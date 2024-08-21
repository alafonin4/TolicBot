package alafonin4.TolikBot.Service;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class YandexDiskUploader {
    private static final String OAUTH_TOKEN = "y0_AgAAAABZPFf-AAxAFAAAAAENa4WOAABHey7tvNZLVZ6d4ZDXdNVZKZ_GCA";
    private static final String DISK_API_BASE_URL = "https://cloud-api.yandex.net/v1/disk/";

    public static String uploadFile(String filePath, byte[] fileBytes) throws IOException {
        OkHttpClient client = new OkHttpClient();

        HttpUrl url = HttpUrl.parse(DISK_API_BASE_URL + "resources/upload").newBuilder()
                .addQueryParameter("path", filePath)
                .addQueryParameter("overwrite", "true")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "OAuth " + OAUTH_TOKEN)
                .get()
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Ошибка при получении ссылки для загрузки: " + response);

        String uploadUrl = new JSONObject(response.body().string()).getString("href");

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), fileBytes);
        Request uploadRequest = new Request.Builder()
                .url(uploadUrl)
                .put(requestBody)
                .build();

        Response uploadResponse = client.newCall(uploadRequest).execute();
        if (!uploadResponse.isSuccessful()) throw new IOException("Ошибка при загрузке файла: " + uploadResponse);
        return filePath;
    }
    public static String publishAndGetPublicLink(String filePath) throws IOException {
        OkHttpClient client = new OkHttpClient();

        // 1. Публикация файла
        HttpUrl publishUrl = HttpUrl.parse(DISK_API_BASE_URL + "resources/publish").newBuilder()
                .addQueryParameter("path", filePath)
                .build();

        Request publishRequest = new Request.Builder()
                .url(publishUrl)
                .addHeader("Authorization", "OAuth " + OAUTH_TOKEN)
                .put(RequestBody.create(null, new byte[0]))
                .build();

        Response publishResponse = client.newCall(publishRequest).execute();
        if (!publishResponse.isSuccessful()) {
            throw new IOException("Ошибка при публикации файла: " + publishResponse);
        }

        // 2. Получение публичной ссылки
        HttpUrl metadataUrl = HttpUrl.parse(DISK_API_BASE_URL + "resources").newBuilder()
                .addQueryParameter("path", filePath)
                .addQueryParameter("fields", "public_url")
                .build();

        Request metadataRequest = new Request.Builder()
                .url(metadataUrl)
                .addHeader("Authorization", "OAuth " + OAUTH_TOKEN)
                .get()
                .build();

        Response metadataResponse = client.newCall(metadataRequest).execute();
        if (!metadataResponse.isSuccessful()) {
            throw new IOException("Ошибка при получении публичной ссылки: " + metadataResponse);
        }

        String publicUrl = new JSONObject(metadataResponse.body().string()).optString("public_url", null);

        if (publicUrl == null) {
            throw new IOException("Публичная ссылка недоступна.");
        }

        return publicUrl;
    }
    public static void createFolderIfNotExists(String folderPath) throws IOException {
        OkHttpClient client = new OkHttpClient();
        var l = folderPath.split("/");
        StringBuilder path = new StringBuilder();

        for (int i = 0; i < l.length; i++) {
            if (i != 0) {
                path.append("/");
            }
            path.append(l[i]);
            HttpUrl checkFolderUrl = HttpUrl.parse(DISK_API_BASE_URL + "resources").newBuilder()
                    .addQueryParameter("path", path.toString())
                    .build();

            Request checkFolderRequest = new Request.Builder()
                    .url(checkFolderUrl)
                    .addHeader("Authorization", "OAuth " + OAUTH_TOKEN)
                    .get()
                    .build();

            Response checkFolderResponse = client.newCall(checkFolderRequest).execute();

            if (checkFolderResponse.code() == 404) { // Если папка не найдена, создаём её
                HttpUrl createFolderUrl = HttpUrl.parse(DISK_API_BASE_URL + "resources").newBuilder()
                        .addQueryParameter("path", path.toString())
                        .build();

                Request createFolderRequest = new Request.Builder()
                        .url(createFolderUrl)
                        .addHeader("Authorization", "OAuth " + OAUTH_TOKEN)
                        .put(RequestBody.create(null, new byte[0]))
                        .build();

                Response createFolderResponse = client.newCall(createFolderRequest).execute();
                if (!createFolderResponse.isSuccessful()) {
                    throw new IOException("Ошибка при создании папки: " + createFolderResponse);
                }
            } else if (!checkFolderResponse.isSuccessful() && checkFolderResponse.code() != 409) {
                throw new IOException("Ошибка при проверке папки: " + checkFolderResponse);
            }
        }
    }
    public static String uploadFileToFolder(String folderPath, String fileName, byte[] fileBytes) throws IOException {
        createFolderIfNotExists(folderPath);

        String fullPath = folderPath + "/" + fileName;
        return uploadFile(fullPath, fileBytes);
    }
}

