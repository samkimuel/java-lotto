package lotto.domain.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.joining;

public class LottoTicket {

  private static final String INVALID_SIZE_OF_NUMBER = "6개의 숫자 구성이 아닙니다.";
  private static final String LEFT_SQUARE_BRACKET = "[";
  private static final String RIGHT_SQUARE_BRACKET = "]";
  private static final String COMMA = ",";
  private static final int LOTTO_SIZE = 6;
  private static final int ZERO = 0;

  private final Set<LottoNumber> numbers;
  private final StringBuilder stringBuilder;

  public LottoTicket(List<LottoNumber> numbers) {
    this.numbers = new HashSet<>(numbers);
    validSize();
    this.stringBuilder = new StringBuilder();
  }

  private void validSize() {
    if (this.numbers.size() != LOTTO_SIZE) {
      throw new IllegalArgumentException(INVALID_SIZE_OF_NUMBER);
    }
  }

  protected boolean contains(LottoNumber lottoNumber) {
    return numbers.contains(lottoNumber);
  }

  public int getMatchedCount(LottoTicket lotto) {
    return (int) this.numbers.stream()
            .filter(lotto::contains)
            .count();
  }

  public int size() {
    return numbers.size();
  }

  protected List<LottoNumber> getNumbers() {
    return Collections.unmodifiableList(new ArrayList<>(numbers));
  }

  public boolean isMatchedBonus(LottoNumber bonusNumber) {
    return numbers.contains(bonusNumber);
  }

  @Override
  public String toString() {
    stringBuilder.setLength(ZERO);
    stringBuilder.append(LEFT_SQUARE_BRACKET)
                 .append(getListToString())
                 .append(RIGHT_SQUARE_BRACKET);
    return stringBuilder.toString();
  }

  private String getListToString() {
    return numbers.stream()
                  .map(lottoNumber -> String.valueOf(lottoNumber.getNumber()))
                  .collect(joining(COMMA));
  }

}
