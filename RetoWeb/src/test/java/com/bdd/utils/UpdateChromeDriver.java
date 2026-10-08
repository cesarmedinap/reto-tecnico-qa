package com.bdd.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.*;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UpdateChromeDriver {

    public static void downloadChromeDriver(String targetDir) {

        System.out.println("Starting ChromeDriver download...");
        System.out.println("Target directory: " + targetDir);

        try {
            String os = detectOS();
            String chromeVersion = getChromeVersion();

            System.out.println("Detected Chrome version: " + chromeVersion);
            System.out.println("Detected OS: " + os);

            String downloadUrl = getChromeDriverDownloadUrl(chromeVersion, os);

            System.out.println("Downloading from: " + downloadUrl);

            Path targetPath = Paths.get(targetDir, "chromedriver.zip");

            Files.createDirectories(Paths.get(targetDir));

            downloadFile(downloadUrl, targetPath);

            unzip(targetPath.toString(), targetDir);

            Files.deleteIfExists(targetPath);

            System.out.println("Downloaded and extracted to: " + targetDir);

        } catch (Exception e) {
            System.err.println("ERROR downloading ChromeDriver:");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static String detectOS() {

        String osName = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);

        if (osName.contains("win")) {
            return "win32";
        }

        if (osName.contains("mac")) {
            String arch = System.getProperty("os.arch");

            return arch.contains("aarch64") || arch.contains("arm")
                    ? "mac-arm64"
                    : "mac-x64";
        }

        if (osName.contains("nix") || osName.contains("nux")) {
            return "linux64";
        }

        throw new UnsupportedOperationException(
                "Unsupported OS: " + osName
        );
    }

    private static String getChromeVersion() throws IOException {

        String[] command = {
                "reg",
                "query",
                "HKEY_CURRENT_USER\\Software\\Google\\Chrome\\BLBeacon",
                "/v",
                "version"
        };

        Process process = new ProcessBuilder(command).start();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        );

        String line;

        while ((line = reader.readLine()) != null) {

            if (line.contains("version")) {

                String version = line
                        .replaceAll("[^0-9.]", "")
                        .trim();

                if (!version.isEmpty()) {
                    return version;
                }
            }
        }

        throw new RuntimeException(
                "Could not determine Chrome version"
        );
    }

    private static String getChromeDriverDownloadUrl(
            String chromeVersion,
            String os
    ) throws Exception {

        String apiUrl =
                "https://googlechromelabs.github.io/chrome-for-testing/known-good-versions-with-downloads.json";

        HttpURLConnection connection =
                (HttpURLConnection) new URL(apiUrl).openConnection();

        connection.setRequestMethod("GET");

        StringBuilder response = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        )) {

            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }

        JSONObject json =
                new JSONObject(response.toString());

        JSONArray versions =
                json.getJSONArray("versions");

        String chromeMajor =
                chromeVersion.split("\\.")[0];

        System.out.println(
                "Looking for ChromeDriver compatible with Chrome major version: "
                        + chromeMajor
        );

        for (int i = versions.length() - 1; i >= 0; i--) {

            JSONObject versionObject =
                    versions.getJSONObject(i);

            String version =
                    versionObject.getString("version");

            String major =
                    version.split("\\.")[0];

            if (!major.equals(chromeMajor)) {
                continue;
            }

            if (!versionObject.has("downloads")) {
                continue;
            }

            JSONObject downloads =
                    versionObject.getJSONObject("downloads");

            if (!downloads.has("chromedriver")) {
                continue;
            }

            JSONArray drivers =
                    downloads.getJSONArray("chromedriver");

            for (int j = 0; j < drivers.length(); j++) {

                JSONObject driver =
                        drivers.getJSONObject(j);

                String platform =
                        driver.getString("platform");

                String url =
                        driver.getString("url");

                System.out.println(
                        "Found compatible driver: "
                                + version
                                + " / "
                                + platform
                );

                if (platform.equals(os)) {
                    return url;
                }
            }
        }

        throw new RuntimeException(
                "Could not find ChromeDriver compatible with Chrome "
                        + chromeVersion
                        + " for platform "
                        + os
        );
    }

    private static void downloadFile(
            String urlStr,
            Path target
    ) throws IOException {

        try (
                BufferedInputStream in =
                        new BufferedInputStream(
                                new URL(urlStr).openStream()
                        );

                FileOutputStream output =
                        new FileOutputStream(target.toFile())
        ) {

            byte[] buffer = new byte[8192];

            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        }
    }

    private static void unzip(
            String zipFilePath,
            String destDir
    ) throws IOException {

        Path destination =
                Paths.get(destDir);

        Files.createDirectories(destination);

        byte[] buffer = new byte[8192];

        try (
                ZipInputStream zis =
                        new ZipInputStream(
                                new FileInputStream(zipFilePath)
                        )
        ) {

            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {

                if (entry.isDirectory()) {
                    continue;
                }

                String fileName =
                        Paths.get(entry.getName())
                                .getFileName()
                                .toString();

                if (!fileName.equalsIgnoreCase("chromedriver")
                        && !fileName.equalsIgnoreCase("chromedriver.exe")) {
                    continue;
                }

                Path outputFile =
                        destination.resolve(
                                System.getProperty("os.name")
                                        .toLowerCase()
                                        .contains("win")
                                        ? "chromedriver.exe"
                                        : "chromedriver"
                        );

                try (OutputStream fos =
                             Files.newOutputStream(
                                     outputFile,
                                     StandardOpenOption.CREATE,
                                     StandardOpenOption.TRUNCATE_EXISTING
                             )) {

                    int len;

                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                }
            }
        }
    }
}
