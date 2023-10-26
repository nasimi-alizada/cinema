package com.example.cinema.service.specification;

import com.example.cinema.criteria.MovieCriteria;
import com.example.cinema.dao.entity.MovieEntity;
import com.example.cinema.util.PredicateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static com.example.cinema.model.constant.CriteriaConst.DIRECTOR;
import static com.example.cinema.model.constant.CriteriaConst.NAME;
import static com.example.cinema.util.PredicateUtil.applyLikePattern;

@AllArgsConstructor
public class MovieSpecification implements Specification<MovieEntity> {
    private MovieCriteria movieCriteria;


    @Override
    public Predicate toPredicate(Root<MovieEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        var predicates = PredicateUtil.builder()
                .addNullSafety(movieCriteria.getName(),
                        name -> cb.like(root.get(NAME), applyLikePattern(name)))
                .addNullSafety(movieCriteria.getDirector(),
                        director -> cb.like(root.get(DIRECTOR), applyLikePattern(director)))
                .build();

        return cb.and(predicates);
    }
}
