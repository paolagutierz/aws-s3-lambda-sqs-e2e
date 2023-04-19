package co.com.bancolombia.certification.upload.files.questions;

import co.com.bancolombia.certification.upload.files.models.ExecutionMemory;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.thucydides.core.annotations.Step;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ValidateResultS3 implements Question<Boolean> {

    @Override
    @Step("{0} validate image was resize")
    public Boolean answeredBy(Actor actor) {
        byte[] thumbnail = ExecutionMemory.getThumbnail();

        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(new ByteArrayInputStream(thumbnail));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int imageWidth= bufferedImage.getWidth();

        return imageWidth==480;
    }

    public static ValidateResultS3 imageWasResize() {
        return new ValidateResultS3();
    }
}
