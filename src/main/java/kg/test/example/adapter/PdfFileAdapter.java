package kg.test.example.adapter;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class PdfFileAdapter implements BookFileFormatAdapter{

    @Override
    public void processFile(MultipartFile file) {

    }
}
