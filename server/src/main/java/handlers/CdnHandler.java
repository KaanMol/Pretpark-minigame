package handlers;

import http.HttpResponse;
import http.StreamHttpResponse;
import io.FileManager;
import java.io.IOException;
import java.io.InputStream;

public class CdnHandler extends Handler {

    @Override
    protected HttpResponse get() throws IOException {
        String file = getParameter("file");

        if (file == null) {
            return error("Missing parameter 'file'");
        }

        InputStream stream = FileManager.getResource(file);

        if (stream == null) {
            return error("Resource '" + file + "' not found");
        }

        byte[] bytes = stream.readAllBytes();
        stream.close();

        String extension = file.substring(file.lastIndexOf('.') + 1);
        String mime = mimeType(extension);

        return new StreamHttpResponse(mime, bytes);
    }

    @Override
    protected HttpResponse post() throws IOException {
        return error("Not implemented");
    }

    @Override
    protected HttpResponse put() throws IOException {
        return error("Not implemented");
    }

    @Override
    protected HttpResponse delete() throws IOException {
        return error("Not implemented");
    }

    private String mimeType(String extension) {
        return switch (extension) {
            case "bmp" -> "image/bmp";
            case "gif" -> "image/gif";
            case "ico" -> "image/x-icon";
            case "jpeg", "jpg" -> "image/jpeg";
            case "png" -> "image/png";
            case "svg" -> "image/svg+xml";
            case "tif", "tiff" -> "image/tiff";
            case "webp" -> "image/webp";
            default -> "application/octet-stream";
        };
    }
}
