package pl.com.MyDiet.MyDiet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.MyDiet.MyDiet.data.model.file.FileEntity;

public interface FileEntityRepository extends JpaRepository<FileEntity, Long> {
}
