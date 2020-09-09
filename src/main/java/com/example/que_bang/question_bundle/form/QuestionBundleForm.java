package question_bundle.form;

import com.example.que_bang.domain.question_bundle.QuestionBundlePaper;
import com.example.que_bang.domain.question_bundle.QuestionBundleTimeZone;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class QuestionBundleForm {
  @NotBlank
  @Email
  private int year;
  @NotBlank
  private int month;
  @NotBlank
  private QuestionBundleTimeZone timeZone;
  @NotBlank
  private QuestionBundlePaper paper; // 시험 종류
}
