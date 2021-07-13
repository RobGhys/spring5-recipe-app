package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {
    // We use the @Synchronized method from Lombok as Spring does not guarantee thread safety
    // This enables to run it in a multi-threaded environment
    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        // It is nullable, so we are able to return a null
        if (source == null) {
            return null;
        }

        // Return an instance of the converted type
        final UnitOfMeasure uom = new UnitOfMeasure(); //immutable variable
        uom.setId(source.getId());
        uom.setDescription(source.getDescription());

        return uom;
    }
}
