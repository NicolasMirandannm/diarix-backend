package nicksolutions.core.crud;

import org.springframework.data.domain.Page;

public interface ApplicationComponent<Dto> {

  Dto getById(String id);
  Dto create(Dto dto);
  Dto update(String id, Dto dto);
  void delete(String id);
  Page<Dto> list(int page, int size);

}
