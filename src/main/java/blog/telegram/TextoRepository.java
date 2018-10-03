package blog.telegram;

import blog.Texto;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rafa on 02/10/18.
 */
public interface TextoRepository extends CrudRepository<Texto, Integer> {
}
