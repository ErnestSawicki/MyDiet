package pl.com.MyDiet.MyDiet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.MyDiet.MyDiet.data.model.file.FileEntity;

import java.util.List;

public interface FileEntityRepository extends JpaRepository<FileEntity, Long> {
     List<FileEntity> findAllById(Long id);
}
