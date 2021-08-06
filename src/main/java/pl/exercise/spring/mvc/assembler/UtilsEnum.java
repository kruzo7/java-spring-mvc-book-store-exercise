package pl.exercise.spring.mvc.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class UtilsEnum {

	public static <E extends Enum<E>> List<String> toString(Collection<E> values) {
		return values.stream().map(p -> p.name()).collect(Collectors.toList());
	}

	// TODO: write generic converter
	// public static <E extends Enum<E>> List<E> toEnum(List<String> values) {
	// E dsds = Enum.valueOf(E, "");
	//
	// return values.stream().map(p -> Enum.valueOf(E,
	// p)).collect(Collectors.toList());
	// }

	// public static <E extends Enum<E>> E toEnum(String value) {
	// return Enum.valueOf(E, value);
	// }

}
