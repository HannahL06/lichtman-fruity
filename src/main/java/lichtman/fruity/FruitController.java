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
    private final FruityService service;
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
            FruityService service,
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
        this.service = service;
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

        Disposable disposable = service.getFruit(fruitName)
                // tells Rx to request the data on a background Thread
                .subscribeOn(Schedulers.io())
                // tells Rx to handle the response on Swing's main Thread
                .observeOn(Schedulers.from(SwingUtilities::invokeLater))
                //.observeOn(AndroidSchedulers.mainThread()) // Instead use this on Android only
                .subscribe(
                        (response) -> handleResponse(response),
                        Throwable::printStackTrace);

    }

    public void handleResponse(Fruit fruit) {
        familyInfo.setText(fruit.family());
        orderInfo.setText(fruit.order());
        genusInfo.setText(fruit.genus());

        Nutritions nutritions = fruit.nutritions();
        caloriesInfo.setText(String.valueOf(nutritions.calories()));
        fatInfo.setText(String.valueOf(nutritions.fat()));
        sugarInfo.setText(String.valueOf(nutritions.sugar()));
        carbsInfo.setText(String.valueOf(nutritions.carbohydrates()));
        proteinInfo.setText(String.valueOf(nutritions.protein()));

        try {
            UnsplashService service = new UnsplashServiceFactory().create();
            ApiKey apiKey = new ApiKey();
            String keyString = apiKey.get();
            Photos photo = service.search(keyString, fruit.name()).blockingGet();

            ImageIcon imageIcon = new ImageIcon(new URL(photo.results()[0].urls().small()));
            picLabel.setIcon(imageIcon);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
