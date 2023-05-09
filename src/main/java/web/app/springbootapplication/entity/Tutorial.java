package web.app.springbootapplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tutorials",indexes = {
        @Index(columnList = "title"),
        @Index(name = "multiIndex1", columnList = "title, description"),
        @Index(name = "multiIndex2", columnList = "description, published")
})
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;
    @Column(name = "published")
    private boolean published;



}
