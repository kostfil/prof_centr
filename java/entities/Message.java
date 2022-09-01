package entities;

import lombok.Builder;
import lombok.Data;

/**
 * Lombok — проект по добавлению дополнительной функциональности в Java c помощью
 * изменения исходного кода перед Java компиляцией.
 */


@Data
/**
 * генерация всех служебных методов,
 * заменяет сразу команды @ToString, @EqualsAndHashCode,
 * @Getter, @Setter, @RequiredArgsConstructor
 */
@Builder
/**
 * реализация паттерна builder,
 * @Singular – используется для объектов в
 * единственном экземпляре (добавления элемента
 * в коллекции и т.п.)
 */
public class Message {
    private String name;
    private String text;
}
