/* package com.avanade.avanade.repository;

import com.avanade.avanade.dto.CategoriaDTO;
import com.avanade.avanade.entity.Categoria;
import com.avanade.avanade.entity.QCategoria;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoriaFilterRepository extends QuerydslRepositorySupport {
  @PersistenceContext
  private EntityManager em;
  public CategoriaFilterRepository() {
    super(Categoria.class);
  }

  public List<Categoria> filter(CategoriaDTO dto) {
    QCategoria categoria = QCategoria.categoria;
    List<Predicate> predicates = new ArrayList<>();
    if (dto.nome() != null) {
       predicates.add(categoria.nome.like("%" + dto.nome() + "%"));
    }

    if (dto.descricao() != null) {
      predicates.add(categoria.descricao.like("%" + dto.descricao() + "%"));
    }

    return new JPAQueryFactory(em)
        .selectFrom(categoria)
        .where(predicates.toArray(new Predicate[0]))
        .fetch();
  }
}
 */