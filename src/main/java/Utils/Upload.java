package Utils;

import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 *
 * @author Le Tan Kim
 */
public class Upload {

    public String uploadFile(Part filePart, String uploadPath) {
        Path folderPath = Paths.get(uploadPath);
        Path parentPath = folderPath.getParent();
        File uploadParent = new File(parentPath.toString());
        if (!uploadParent.exists()) {
            uploadParent.mkdir();
        }
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String fileName = extractFileName(filePart);
        if (fileName == null) {
            return null;
        }
        String filePath = uploadPath + File.separator + fileName;
        try ( InputStream fileContent = filePart.getInputStream()) {
            Files.copy(fileContent, new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException er) {
            return null;
        }
        return fileName;
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        SimpleDateFormat d = new SimpleDateFormat("MMddyyhhmmss");
        String date = d.format(new Date());
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String path = s.substring(s.indexOf("=") + 2, s.length() - 1);
                if (path.equals("")) {
                    return null;
                }
                return date + "_" + this.removeUnicode(path);
            }
        }
        return "";
    }

    public String removeUnicode(String input) {
        String normalizedString = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String stringWithoutDiacritics = pattern.matcher(normalizedString).replaceAll("");
        String[] search = {
            "(à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ)",
            "(è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ)",
            "(ì|í|ị|ỉ|ĩ)",
            "(ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ)",
            "(ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ)",
            "(ỳ|ý|ỵ|ỷ|ỹ)",
            "(đ)",
            "(À|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ)",
            "(È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ)",
            "(Ì|Í|Ị|Ỉ|Ĩ)",
            "(Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ)",
            "(Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ)",
            "(Ỳ|Ý|Ỵ|Ỷ|Ỹ)",
            "(Đ)"
        };
        String[] replace = {
            "a",
            "e",
            "i",
            "o",
            "u",
            "y",
            "d",
            "A",
            "E",
            "I",
            "O",
            "U",
            "Y",
            "D",};
        for (int i = 0; i < search.length; i++) {
            stringWithoutDiacritics = stringWithoutDiacritics.replaceAll(search[i], replace[i]);
        }
        return stringWithoutDiacritics;
    }
}
