package gr.aueb.cf.project8.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
    
public class ReportFaultDTO {
    private String location;
    private String faultType;
    private String description;
    private String contactInfo;
    private MultipartFile photo; // For file uploads
    private String photoBase64; // New field for base64 photo
}
