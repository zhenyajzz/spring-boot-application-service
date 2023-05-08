package web.app.springbootapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tutorial_details")
public class TutorialDetails {

    @Id
    private Long id;

    @Column
    private Date createdOn;

    @Column
    private String createdBy;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "tutorial_id")
    private Tutorial tutorial;
}
