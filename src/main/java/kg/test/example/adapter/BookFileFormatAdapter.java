package kg.test.example.adapter;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public interface BookFileFormatAdapter {
    void processFile(MultipartFile file);


}
