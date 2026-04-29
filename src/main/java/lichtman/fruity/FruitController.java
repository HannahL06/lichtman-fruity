package lichtman.fruity;

import com.andrewoid.apikeys.ApiKey;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lichtman.fruity.unsplash.Photos;
import lichtman.fruity.unsplash.UnsplashService;
import lichtman.fruity.unsplash.UnsplashServiceFactory;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

public class FruitController {
    private final FruityService fruityService;
    private final UnsplashService unsplashService;
    private final JLabel picLabel;
    private final JLabel familyInfo;
    private final JLabel orderInfo;
    private final JLabel genusInfo;
    private final JLabel caloriesInfo;
    private final JLabel fatInfo;
    private final JLabel sugarInfo;
    private final JLabel carbsInfo;
    private final JLabel proteinInfo;

    public FruitController(
            FruityService fruityService,
            UnsplashService unsplashService,
            JLabel picLabel,
            JLabel familyInfo,
            JLabel orderInfo,
            JLabel genusInfo,
            JLabel caloriesInfo,
            JLabel fatInfo,
            JLabel sugarInfo,
            JLabel carbsInfo,
            JLabel proteinInfo
    ) {
        this.fruityService = fruityService;
        this.unsplashService = unsplashService;
        this.picLabel = picLabel;
        this.familyInfo = familyInfo;
        this.orderInfo = orderInfo;
        this.genusInfo = genusInfo;
        this.caloriesInfo = caloriesInfo;
        this.fatInfo = fatInfo;
        this.sugarInfo = sugarInfo;
        this.carbsInfo = carbsInfo;
        this.proteinInfo = proteinInfo;

    }

    public void fruitInfo(String fruitName) {

        Disposable fDisposable = fruityService.getFruit(fruitName)
                // tells Rx to request the data on a background Thread
                .subscribeOn(Schedulers.io())
                // tells Rx to handle the response on Swing's main Thread
                .observeOn(Schedulers.from(SwingUtilities::invokeLater))
                //.observeOn(AndroidSchedulers.mainThread()) // Instead use this on Android only
                .subscribe(
                        (fruit) -> handleFruitResponse(fruit),
                        Throwable::printStackTrace);

        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();
        Disposable uDisposable = unsplashService.search(keyString, fruitName)
                // tells Rx to request the data on a background Thread
                .subscribeOn(Schedulers.io())
                // tells Rx to handle the response on Swing's main Thread
                .observeOn(Schedulers.from(SwingUtilities::invokeLater))
                //.observeOn(AndroidSchedulers.mainThread()) // Instead use this on Android only
                .subscribe(
                        (image) -> handleImageResponse(image),
                        Throwable::printStackTrace);

    }

    public void handleFruitResponse(Fruit fruit) {
        familyInfo.setText(fruit.family());
        orderInfo.setText(fruit.order());
        genusInfo.setText(fruit.genus());

        Nutritions nutritions = fruit.nutritions();
        caloriesInfo.setText(String.valueOf(nutritions.calories()));
        fatInfo.setText(String.valueOf(nutritions.fat()));
        sugarInfo.setText(String.valueOf(nutritions.sugar()));
        carbsInfo.setText(String.valueOf(nutritions.carbohydrates()));
        proteinInfo.setText(String.valueOf(nutritions.protein()));

    }

    public void handleImageResponse(Photos image) {
        try {
            ImageIcon imageIcon = new ImageIcon(new URL(image.results()[0].urls().small()));
            picLabel.setIcon(imageIcon);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
