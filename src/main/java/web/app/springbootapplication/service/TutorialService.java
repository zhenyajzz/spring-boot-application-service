package web.app.springbootapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import web.app.springbootapplication.entity.Tutorial;
import web.app.springbootapplication.repository.TutorialRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TutorialService {

    @Autowired
    TutorialRepository tutorialRepository;

    public List<Tutorial> getAllTutorials(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Tutorial> pagedResult = tutorialRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();

        } else {
            return new ArrayList<Tutorial>();
        }
    }
    }