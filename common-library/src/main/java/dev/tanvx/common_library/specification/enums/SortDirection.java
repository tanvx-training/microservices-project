package dev.tanvx.common_library.specification.enums;

import dev.tanvx.common_library.specification.request.SortRequest;
import jakarta.persistence.criteria.*;

public enum SortDirection {

  ASC {
    public <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequest request) {
      return cb.asc(root.get(request.getKey()));
    }
  },
  DESC {
    public <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequest request) {
      return cb.desc(root.get(request.getKey()));
    }
  };

  public abstract <T> Order build(Root<T> root, CriteriaBuilder cb, SortRequest request);
}