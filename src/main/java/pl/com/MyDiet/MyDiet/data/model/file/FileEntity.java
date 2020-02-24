package pl.com.MyDiet.MyDiet.data.model.file;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.com.MyDiet.MyDiet.data.model.EntityBase;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @ToString(exclude = {"data"})
public class FileEntity extends EntityBase {
    @Column(name = "file_name", nullable = false)
    private String fileName;
    @Column(name = "content_type", nullable = false)
    private String contentType;
    @Lob
    @Basic(fetch = FetchType.LAZY, optional = false)
    @Column(name = "data", nullable = false, columnDefinition = "MEDIUMBLOB")
    private byte[] data;
}
