package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.shape.*;
import javafx.util.Duration;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import static java.lang.Math.*;


public class MainGraph extends Application {

    private static Group root = new Group();
    private static Scene scene = new Scene(root, 0, 0);
    private static AnimationTimer animationTimer;
    private static MediaPlayer audioPlayerForStartMenu;
    private static MediaPlayer audioPlayerForFlipPage;
    private static Polygon triangle;
    private static String password;
    private static String command;
    private static StackPane stackPane;
    private static Account account;
    private static ImageView loadingImage;
    private static Rectangle backGroundRectLoading = new Rectangle(2000, 2000);
    private static Label backGroundLableLoading = new Label("LOADING");
    private static ImageView mainMenuBackgroundView;
    private static MediaPlayer backGroundAudioStartInAccount;
    private static AnimationTimer animationTimerForBackGroundMusic;
    private static Integer page = 1;
    private static Group group;
    private static String imageFolder;

    public static String getPassword() {
        return password;
    }

    public static String getCommand() {
        return command;
    }

    static {
        File audioFile = new File("sound.mp3");
        Media audio = new Media(audioFile.toURI().toString());
        audioPlayerForStartMenu = new MediaPlayer(audio);
    }

    static {
        try {
            Image image = new Image(new FileInputStream("src/sample/startMenuAttack.gif"));
            loadingImage = new ImageView(image);
        } catch (Exception e) {

        }
    }

    public void setLoading() {

        try {

            Font font = Font.loadFont(new FileInputStream("src/sample/font.ttf"), 15);
            backGroundLableLoading.setScaleX(3);
            backGroundLableLoading.setScaleY(2);
            backGroundLableLoading.relocate(1100, 390);
            backGroundLableLoading.setStyle("-fx-font-weight: bold;");
            backGroundLableLoading.setFont(font);
            backGroundLableLoading.setTextFill(Color.WHITE);
            loadingImage.relocate(1070, 200);
            root.getChildren().addAll(loadingImage, backGroundLableLoading);

        } catch (Exception e) {
        }

    }

    public void deleteLoading() {
        root.getChildren().removeAll(backGroundRectLoading, loadingImage, backGroundLableLoading);
    }

    public void setSoundForFlipPage() {
        File audioFile = new File("pageFlip.mp3");
        Media audio = new Media(audioFile.toURI().toString());
        audioPlayerForFlipPage = new MediaPlayer(audio);
    }

    public void startGameEnviroment() throws Exception {

        setCircleEffectInStartMenu();

        scene.setFill(Color.BLACK);
        Image duelystLogo = new Image(new FileInputStream("src/sample/DuelystLogo.png"));
        ImageView duelystView = new ImageView(duelystLogo);
        duelystView.setX(532);
        duelystView.setY(170);
        root.getChildren().add(duelystView);

        putGifInStartMenu();
        setLoadingLable();

        setMouse();

        audioPlayerForStartMenu.play();

        PauseTransition delay = new PauseTransition(Duration.millis(300));
        delay.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    root.getChildren().clear();
                    animationTimer.stop();
                    audioPlayerForStartMenu.stop();
                    setMainMenuBackGround();
                } catch (Exception e) {

                }
            }
        });
        delay.play();

    }

    public void setLoadingLable() {

        Label label = new Label("LOADING");
        label.setScaleX(5);
        label.setScaleY(4);
        label.relocate(650, 520);
        label.setStyle("font-weight: bold;");
        label.setTextFill(Color.rgb(255, 255, 255, 0.15));

        Label label1 = new Label("This may take a moment");
        label1.setScaleX(2);
        label1.setScaleY(2);
        label1.relocate(570, 585);
        label1.setStyle("font-weight: bold;");
        label1.setTextFill(Color.rgb(255, 255, 255, 0.4));

        Label label2 = new Label(".");
        label2.setScaleX(2);
        label2.setScaleY(2);
        label2.setStyle("font-weight: bold;");
        label2.relocate(830, 585);
        label2.setTextFill(Color.rgb(255, 255, 255, 0.4));

        Label label4 = new Label(".");
        label4.setScaleX(2);
        label4.setScaleY(2);
        label4.setStyle("font-weight: bold;");
        label4.relocate(850, 585);
        label4.setTextFill(Color.rgb(255, 255, 255, 0.4));

        Label label3 = new Label(".");
        label3.setScaleX(2);
        label3.setScaleY(2);
        label3.setStyle("font-weight: bold;");
        label3.relocate(870, 585);
        label3.setTextFill(Color.rgb(255, 255, 255, 0.4));

        root.getChildren().addAll(label, label1);

        animationTimer = new AnimationTimer() {
            private long lastTime;
            private int loop;

            @Override
            public void handle(long now) {
                if (lastTime == 0) {
                    lastTime = now;
                }
                if (now - lastTime > 800000000) {
                    lastTime = now;
                    if (loop % 4 == 0) {
                        root.getChildren().removeAll(label2, label3, label4);
                    } else if (loop % 4 == 1) {
                        root.getChildren().removeAll(label2, label3, label4);
                        root.getChildren().addAll(label2);
                    } else if (loop % 4 == 2) {
                        root.getChildren().removeAll(label2, label3, label4);
                        root.getChildren().addAll(label2, label4);
                    } else {
                        root.getChildren().removeAll(label2, label3, label4);
                        root.getChildren().addAll(label2, label3, label4);
                    }
                    loop++;
                }
            }
        };
        animationTimer.start();

    }

    public void putGifInStartMenu() throws Exception {
        Image startMenuGif = new Image(new FileInputStream("src/sample/startMenuAttack.gif"));
        ImageView startMenuView = new ImageView(startMenuGif);
        startMenuView.setX(510);
        startMenuView.setY(200);
        startMenuView.setFitWidth(360);
        startMenuView.setFitHeight(290);
        root.getChildren().add(startMenuView);
    }

    public void setCircleEffectInStartMenu() {
        Group circles = new Group();
        for (int i = 0; i < 30; i++) {
            Circle circle = new Circle(150, Color.web("white", 0.05));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white", 0.16));
            circle.setStrokeWidth(4);
            circles.getChildren().add(circle);
        }
        Rectangle colors = new Rectangle(1500, 1200,
                new LinearGradient(0f, 1f, 1f, 0f, true, CycleMethod.NO_CYCLE, new Stop[]{
                        new Stop(0, Color.web("#f8bd55")),
                        new Stop(0.14, Color.web("#c0fe56")),
                        new Stop(0.28, Color.web("#5dfbc1")),
                        new Stop(0.43, Color.web("#64c2f8")),
                        new Stop(0.57, Color.web("#be4af7")),
                        new Stop(0.71, Color.web("#ed5fc2")),
                        new Stop(0.85, Color.web("#ef504c")),
                        new Stop(1, Color.web("#f2660f")),}));
        Group blendModeGroup =
                new Group(new Group(new Rectangle(scene.getWidth(), scene.getHeight(),
                        Color.BLACK), circles), colors);
        colors.setBlendMode(BlendMode.OVERLAY);
        root.getChildren().add(blendModeGroup);
        circles.setEffect(new BoxBlur(10, 10, 3));
        Timeline timeline = new Timeline();
        for (Node circle : circles.getChildren()) {
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(circle.translateXProperty(), random() * 1300),
                            new KeyValue(circle.translateYProperty(), random() * 1000)),
                    new KeyFrame(new Duration(40000),
                            new KeyValue(circle.translateXProperty(), random() * 1300),
                            new KeyValue(circle.translateYProperty(), random() * 1000)));
        }
        timeline.play();
    }

    public void setSoundOfMouse() {
        File audioFile = new File("mouseSound.mp3");
        Media audio = new Media(audioFile.toURI().toString());
        audioPlayerForStartMenu = new MediaPlayer(audio);
        audioPlayerForStartMenu.play();
    }

    public void setMainMenuBackGround() throws Exception {

        Image mainMenuBackGround = new Image(new FileInputStream("src/sample/MainMenuBackGround.jpg"));
        mainMenuBackgroundView = new ImageView(mainMenuBackGround);
        mainMenuBackgroundView.setFitHeight(768);
        mainMenuBackgroundView.setFitWidth(1366);
        root.getChildren().add(mainMenuBackgroundView);

        Rectangle loginBox = new Rectangle(380, 480);
        loginBox.setFill(Color.rgb(105, 105, 104, 0.93));
        loginBox.relocate(940, 90);
        root.getChildren().add(loginBox);

        Label loginLable = new Label("LOG IN");
        loginLable.setTextFill(Color.WHITE);
        loginLable.relocate(1060, 148);
        loginLable.setScaleX(1.25);
        loginLable.setScaleY(1.25);
        root.getChildren().add(loginLable);

        Label signUpLable = new Label("SIGN UP");
        signUpLable.setTextFill(Color.WHITE);
        signUpLable.setOpacity(0.7);
        signUpLable.relocate(1160, 148);
        signUpLable.setScaleX(1.25);
        signUpLable.setScaleY(1.25);
        root.getChildren().add(signUpLable);

        TextField useNameForLoginField = new TextField();
        useNameForLoginField.setPromptText("Username");
        useNameForLoginField.setFocusTraversable(false);
        useNameForLoginField.setScaleX(1.92);
        useNameForLoginField.setScaleY(1.58);
        useNameForLoginField.setStyle("-fx-control-inner-background: #8C8484");
        useNameForLoginField.relocate(1046, 230);
        root.getChildren().add(useNameForLoginField);

        PasswordField passwordForLoginField = new PasswordField();
        passwordForLoginField.setPromptText("Password");
        passwordForLoginField.setFocusTraversable(false);
        passwordForLoginField.setScaleX(1.92);
        passwordForLoginField.setScaleY(1.58);
        passwordForLoginField.setStyle("-fx-control-inner-background: #8C8484");
        passwordForLoginField.relocate(1046, 310);
        root.getChildren().add(passwordForLoginField);

        Button loginButton = new Button("LOG IN");
        loginButton.setTextFill(Color.WHITE);
        loginButton.setStyle("-fx-background-color: #FE7809");
        loginButton.setScaleX(5);
        loginButton.setScaleY(1.8);
        loginButton.relocate(1100, 490);
        root.getChildren().add(loginButton);

        setTriangleAsCursor(0);

        signUpLable.setOnMouseEntered(event -> {
            signUpLable.setOpacity(1);
        });

        signUpLable.setOnMouseExited(event -> {
            if (loginLable.getOpacity() == 1) {
                signUpLable.setOpacity(0.7);
            }
        });

        loginLable.setOnMouseEntered(event -> {
            loginLable.setOpacity(1);
        });

        loginLable.setOnMouseExited(event -> {
            if (signUpLable.getOpacity() == 1) {
                loginLable.setOpacity(0.7);
            }
        });

        loginButton.setOnMouseEntered(event -> {
            loginButton.setStyle("-fx-background-color: #FE0104");
        });

        loginButton.setOnMouseExited(event -> {
            loginButton.setStyle("-fx-background-color: #FE7809");
        });

        signUpLable.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundForFlipPage();
                audioPlayerForFlipPage.play();
                loginLable.setOpacity(0.7);
                signUpLable.setOpacity(1);
                setTriangleAsCursor(1);
                loginButton.setText("SIGN UP");
                loginButton.setScaleX(4.4);
                loginButton.relocate(1095, 490);
                passwordForLoginField.clear();
                useNameForLoginField.clear();
                root.getChildren().remove(stackPane);
            }
        });

        loginLable.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundForFlipPage();
                audioPlayerForFlipPage.play();
                loginLable.setOpacity(1);
                signUpLable.setOpacity(0.7);
                setTriangleAsCursor(0);
                loginButton.setText("LOG IN");
                loginButton.setScaleX(5);
                loginButton.relocate(1100, 490);
                passwordForLoginField.clear();
                useNameForLoginField.clear();
                root.getChildren().remove(stackPane);
            }
        });

        loginButton.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                if (loginButton.getText().matches("SIGN UP")) {
                    command = "create account [" + useNameForLoginField.getText() + "]";
                    password = passwordForLoginField.getText();

                    boolean createAccount = MainMenu.menu();
                    if (!createAccount) {
                        if (stackPane == null || stackPane.getChildren().size() == 0) {
                            errorForSameUserName("Choose another username", false, 700, 227, 1, 1, 1800);
                        }
                    } else {
                        useNameForLoginField.clear();
                        passwordForLoginField.clear();
                    }
                } else {
                    command = "login [" + useNameForLoginField.getText() + "]";
                    password = passwordForLoginField.getText();
                    boolean loginAccount = MainMenu.menu();
                    if (loginAccount) {

                        root.getChildren().removeAll(passwordForLoginField, useNameForLoginField, loginButton, stackPane);
                        setLoading();
                        PauseTransition delay = new PauseTransition(Duration.millis(1800));
                        delay.setOnFinished(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                try {
                                    deleteLoading();
                                    account = MainMenu.getAccounts().get(MainMenu.indexOfAccount(useNameForLoginField.getText()));
                                    if (account.getProfilePhoto().matches("")) {
                                        setProfilePhoto();
                                    } else {
                                        setBackGroundMusic();
                                        inAccount();
                                    }
                                } catch (Exception e) {

                                }
                            }
                        });
                        delay.play();
                    } else {
                        errorForSameUserName("User or pass is invalid", false, 700, 227, 1, 1, 1800);
                    }
                }
            }
        });

    }

    public void setProfilePhoto() throws Exception {

        clear(1);
        group = new Group();
        group.setScaleX(0.1);
        group.setScaleY(0.1);

        Image image = new Image(new FileInputStream("src/sample/shopBackGround.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(450);
        imageView.setFitHeight(650);
        imageView.relocate(450, 60);
        group.getChildren().add(imageView);

        Image image1 = new Image(new FileInputStream("src/profilePhoto/1.jpg"));
        ImagePattern imagePattern = new ImagePattern(image1);
        Circle circle = new Circle(575, 150, 70);
        circle.setFill(imagePattern);
        circle.setOpacity(0.75);
        group.getChildren().add(circle);

        Image image2 = new Image(new FileInputStream("src/profilePhoto/2.jpg"));
        ImagePattern imagePattern1 = new ImagePattern(image2);
        Circle circle1 = new Circle(775, 150, 70);
        circle1.setFill(imagePattern1);
        circle1.setOpacity(0.75);
        group.getChildren().add(circle1);

        Image image3 = new Image(new FileInputStream("src/profilePhoto/3.jpg"));
        ImagePattern imagePattern2 = new ImagePattern(image3);
        Circle circle2 = new Circle(575, 310, 70);
        circle2.setFill(imagePattern2);
        circle2.setOpacity(0.75);
        group.getChildren().add(circle2);

        Image image4 = new Image(new FileInputStream("src/profilePhoto/4.jpg"));
        ImagePattern imagePattern3 = new ImagePattern(image4);
        Circle circle3 = new Circle(775, 310, 70);
        circle3.setFill(imagePattern3);
        circle3.setOpacity(0.75);
        group.getChildren().add(circle3);

        Image image5 = new Image(new FileInputStream("src/profilePhoto/5.jpg"));
        ImagePattern imagePattern4 = new ImagePattern(image5);
        Circle circle4 = new Circle(575, 470, 70);
        circle4.setFill(imagePattern4);
        circle4.setOpacity(0.75);
        group.getChildren().add(circle4);

        Image image6 = new Image(new FileInputStream("src/profilePhoto/6.jpg"));
        ImagePattern imagePattern5 = new ImagePattern(image6);
        Circle circle5 = new Circle(775, 470, 70);
        circle5.setFill(imagePattern5);
        circle5.setOpacity(0.75);
        group.getChildren().add(circle5);

        Image image7 = new Image(new FileInputStream("src/profilePhoto/7.jpg"));
        ImagePattern imagePattern6 = new ImagePattern(image7);
        Circle circle6 = new Circle(675, 630, 70);
        circle6.setFill(imagePattern6);
        circle6.setOpacity(0.75);
        group.getChildren().add(circle6);

        root.getChildren().add(group);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(400), group);
        scaleTransition.setByX(1);
        scaleTransition.setByY(1);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();

        circle.setOnMouseEntered(event -> {
            circle.setOpacity(1);
        });

        circle.setOnMouseExited(event -> {
            circle.setOpacity(0.75);
        });

        circle1.setOnMouseEntered(event -> {
            circle1.setOpacity(1);
        });

        circle1.setOnMouseExited(event -> {
            circle1.setOpacity(0.75);
        });

        circle2.setOnMouseEntered(event -> {
            circle2.setOpacity(1);
        });

        circle2.setOnMouseExited(event -> {
            circle2.setOpacity(0.75);
        });

        circle3.setOnMouseEntered(event -> {
            circle3.setOpacity(1);
        });

        circle3.setOnMouseExited(event -> {
            circle3.setOpacity(0.75);
        });

        circle4.setOnMouseEntered(event -> {
            circle4.setOpacity(1);
        });

        circle4.setOnMouseExited(event -> {
            circle4.setOpacity(0.75);
        });

        circle5.setOnMouseEntered(event -> {
            circle5.setOpacity(1);
        });

        circle5.setOnMouseExited(event -> {
            circle5.setOpacity(0.75);
        });

        circle6.setOnMouseEntered(event -> {
            circle6.setOpacity(1);
        });

        circle6.setOnMouseExited(event -> {
            circle6.setOpacity(0.75);
        });

        circle.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                account.setProfilePhoto("1.jpg");
                try {
                    setBackGroundMusic();
                    inAccount();
                } catch (Exception e) {

                }
            }
        });

        circle1.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                account.setProfilePhoto("2.jpg");
                try {
                    setBackGroundMusic();
                    inAccount();
                } catch (Exception e) {

                }
            }
        });

        circle2.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                account.setProfilePhoto("3.jpg");
                try {
                    setBackGroundMusic();
                    inAccount();
                } catch (Exception e) {

                }
            }
        });

        circle3.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                account.setProfilePhoto("4.jpg");
                try {
                    setBackGroundMusic();
                    inAccount();
                } catch (Exception e) {

                }
            }
        });
        circle4.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                account.setProfilePhoto("5.jpg");
                try {
                    setBackGroundMusic();
                    inAccount();
                } catch (Exception e) {

                }
            }
        });
        circle5.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                account.setProfilePhoto("6.jpg");
                try {
                    setBackGroundMusic();
                    inAccount();
                } catch (Exception e) {

                }
            }
        });
        circle6.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                account.setProfilePhoto("7.jpg");
                try {
                    setBackGroundMusic();
                    inAccount();
                } catch (Exception e) {

                }
            }
        });

    }

    public void errorForSameUserName(String errorString, boolean font, int x, int y, double scaleX, double scaleY, int time) {

        try {

            Font font1 = Font.loadFont(new FileInputStream("src/sample/font.ttf"), 20);
            stackPane = new StackPane();
            Rectangle rectangle = new Rectangle(210, 32);
            rectangle.setFill(Color.RED);
            rectangle.setArcHeight(30);
            rectangle.setArcWidth(30);
            Label label = new Label(errorString);
            label.setScaleX(scaleX);
            label.setScaleY(scaleY);
            label.setTextFill(Color.WHITE);
            stackPane.relocate(x, y);
            if (!font) {
                stackPane.getChildren().add(rectangle);
            } else {
                label.setFont(font1);
            }
            stackPane.getChildren().add(label);
            root.getChildren().remove(stackPane);
            stackPane.setScaleY(0.1);
            stackPane.setScaleX(0.1);
            root.getChildren().add(stackPane);

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(350), stackPane);
            scaleTransition.setByX(1.1);
            scaleTransition.setByY(1.1);
            scaleTransition.setAutoReverse(true);

            scaleTransition.play();


            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(time);
                    } catch (Exception e) {
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            stackPane.getChildren().clear();
                        }
                    });
                }
            });
            thread.start();

        } catch (Exception e) {

        }

    }

    public void setBackGroundMusic() {

        File audioFile = new File("BackGroundMusic.mp3");
        Media audio = new Media(audioFile.toURI().toString());
        backGroundAudioStartInAccount = new MediaPlayer(audio);
        backGroundAudioStartInAccount.setVolume(0.35);
        backGroundAudioStartInAccount.play();

        animationTimerForBackGroundMusic = new AnimationTimer() {
            @Override
            public void handle(long now) {

                backGroundAudioStartInAccount.setOnEndOfMedia(new Runnable() {
                    @Override
                    public void run() {

                        backGroundAudioStartInAccount = new MediaPlayer(audio);
                        backGroundAudioStartInAccount.play();

                    }
                });

            }
        };
        animationTimerForBackGroundMusic.start();

    }

    public void animationForLable(int x, int y) {

        animationTimer = new AnimationTimer() {
            private int i;
            private ImageView imageView;
            private long lastTime;

            @Override
            public void handle(long now) {

                if (lastTime == 0) {
                    lastTime = now;
                }

                if (now - lastTime > 150000000) {
                    try {
                        root.getChildren().remove(imageView);
                        Image image2 = new Image(new FileInputStream("src/sample/" + (i + 1) + ".png"));
                        imageView = new ImageView(image2);
                        imageView.relocate(x, y);
                        root.getChildren().add(imageView);
                    } catch (Exception e) {

                    }

                    if (i == 29) {
                        i = 0;
                    } else {
                        i++;
                    }
                    lastTime = now;
                }

            }
        };
        animationTimer.start();

    }

    public void inAccount() throws Exception {

        for (int i = 0; i < root.getChildren().size(); i++) {
            if (root.getChildren().get(i).equals(mainMenuBackgroundView)) {
                continue;
            } else {
                root.getChildren().remove(i);
                i--;
            }
        }

        Image image = new Image(new FileInputStream("src/sample/backgroundCoin.png"));
        ImageView backGroundCoinView = new ImageView(image);
        backGroundCoinView.setScaleX(2.1);
        backGroundCoinView.setScaleY(1.3);
        backGroundCoinView.relocate(670, 625);
        root.getChildren().add(backGroundCoinView);

        Image image1 = new Image(new FileInputStream("src/sample/CoinGif.gif"));
        ImageView coinView = new ImageView(image1);
        coinView.setScaleX(0.2);
        coinView.setScaleY(0.2);
        coinView.relocate(-20, 300);
        root.getChildren().add(coinView);

        Label budgetLable = new Label(Integer.toString(account.getBudget()));
        budgetLable.setTextFill(Color.WHITE);
        budgetLable.relocate(720, 627);
        budgetLable.setScaleX(2.5);
        budgetLable.setScaleY(1.8);
        Font font = Font.loadFont(new FileInputStream("src/sample/font.ttf"), 20);
        budgetLable.setStyle("font-weight: bold;");
        budgetLable.setFont(font);
        root.getChildren().add(budgetLable);

        Label playLable = new Label("Play");
        playLable.setStyle("font-weight: bold;");
        playLable.setFont(font);
        playLable.relocate(330, 305);
        playLable.setTextFill(Color.WHITE);
        playLable.setScaleX(2.4);
        playLable.setScaleY(1.4);
        playLable.setOpacity(0.8);
        root.getChildren().add(playLable);

        Label collectionLable = new Label("Collection");
        collectionLable.setStyle("font-weight: bold;");
        collectionLable.setFont(font);
        collectionLable.relocate(357, 405);
        collectionLable.setTextFill(Color.WHITE);
        collectionLable.setScaleX(2.4);
        collectionLable.setScaleY(1.4);
        collectionLable.setOpacity(0.8);
        root.getChildren().add(collectionLable);

        Image image2 = new Image(new FileInputStream("src/sample/1.png"));
        ImageView imageView = new ImageView(image2);
        imageView.relocate(250, 300);
        root.getChildren().add(imageView);

        ImageView imageView1 = new ImageView(image2);
        imageView1.relocate(250, 400);
        root.getChildren().add(imageView1);

        Image image3 = new Image(new FileInputStream("src/sample/profile.png"));
        ImageView imageView2 = new ImageView(image3);
        imageView2.relocate(920, 608);
        imageView2.setOpacity(0.8);
        root.getChildren().add(imageView2);

        Image image4 = new Image(new FileInputStream("src/sample/shop.png"));
        ImageView imageView3 = new ImageView(image4);
        imageView3.relocate(1080, 628);
        imageView3.setOpacity(0.8);
        root.getChildren().add(imageView3);

        Label matchHistoryLable = new Label("Match History");
        matchHistoryLable.setTextFill(Color.WHITE);
        matchHistoryLable.setFont(font);
        matchHistoryLable.setScaleY(1.2);
        matchHistoryLable.setScaleY(1.2);
        matchHistoryLable.relocate(911, 700);
        root.getChildren().add(matchHistoryLable);

        Label shopLable = new Label("Shop");
        shopLable.setTextFill(Color.WHITE);
        shopLable.setScaleY(1.2);
        shopLable.setScaleX(1.2);
        shopLable.setFont(font);
        shopLable.relocate(1088, 700);
        root.getChildren().add(shopLable);

        Image image5 = new Image(new FileInputStream("src/sample/logout.png"));
        ImageView imageView4 = new ImageView(image5);
        imageView4.relocate(1190, 630);
        imageView4.setOpacity(0.7);
        root.getChildren().add(imageView4);

        Label logoutLable = new Label("Logout");
        logoutLable.setFont(font);
        logoutLable.setTextFill(Color.WHITE);
        logoutLable.setScaleX(1.3);
        logoutLable.setScaleY(1.25);
        logoutLable.relocate(1250, 638);
        root.getChildren().add(logoutLable);

        imageView2.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                clear(1);
                try {
                    profileShow();
                } catch (Exception e) {

                }
            }
        });

        imageView4.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                animationTimerForBackGroundMusic.stop();
                backGroundAudioStartInAccount.stop();
                root.getChildren().clear();
                try {
                    setMainMenuBackGround();
                } catch (Exception e) {

                }
            }
        });

        imageView4.setOnMouseEntered(event -> {
            imageView4.setOpacity(1);
        });

        imageView4.setOnMouseExited(event -> {
            imageView4.setOpacity(0.7);
        });

        logoutLable.setOnMouseEntered(event -> {
            imageView4.setOpacity(1);
        });

        logoutLable.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                animationTimerForBackGroundMusic.stop();
                backGroundAudioStartInAccount.stop();
                root.getChildren().clear();
                try {
                    setMainMenuBackGround();
                } catch (Exception e) {

                }
            }
        });

        playLable.setOnMouseEntered(event -> {
            deleteImageFromLable(250.0, 300.0);
            animationForLable(250, 300);
            playLable.setOpacity(1);
        });

        playLable.setOnMouseExited(event -> {
            animationTimer.stop();
            playLable.setOpacity(0.8);
        });

        collectionLable.setOnMouseEntered(event -> {
            deleteImageFromLable(250.0, 400.0);
            animationForLable(250, 400);
            collectionLable.setOpacity(1);
        });

        collectionLable.setOnMouseExited(event -> {
            animationTimer.stop();
            collectionLable.setOpacity(0.8);
        });

        imageView2.setOnMouseEntered(event -> {
            imageView2.setOpacity(1);
        });

        imageView2.setOnMouseExited(event -> {
            imageView2.setOpacity(0.8);
        });

        imageView3.setOnMouseEntered(event -> {
            imageView3.setOpacity(1);
        });

        imageView3.setOnMouseExited(event -> {
            imageView3.setOpacity(0.8);
        });

        imageView3.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                try {
                    clear(1);
                    setSoundOfMouse();
                    shop();
                } catch (Exception e) {

                }
            }
        });

    }

    public void profileShow() throws Exception {

        Font font = Font.loadFont(new FileInputStream("src/sample/font.ttf"), 20);
        MainMenu.rankAccounts();

        Image image = new Image(new FileInputStream("src/sample/shopBackGround.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(768);
        imageView.setFitWidth(1366);
        imageView.setOpacity(0.7);
        root.getChildren().add(imageView);

        Image image1 = new Image(new FileInputStream("src/sample/profileBackGround.png"));
        ImageView imageView1 = new ImageView(image1);
        imageView1.relocate(90, -120);
        root.getChildren().add(imageView1);

        Image image2 = new Image(new FileInputStream("src/sample/closeButton.png"));
        ImageView imageView2 = new ImageView(image2);
        imageView2.setOpacity(0.7);
        imageView2.relocate(1180, 178);
        root.getChildren().add(imageView2);

        Circle circle1 = new Circle(350, 200, 45);
        circle1.setFill(Color.RED);
        root.getChildren().add(circle1);

        Image image3 = new Image(new FileInputStream("src/profilePhoto/" + account.getProfilePhoto()));
        ImagePattern imagePattern = new ImagePattern(image3);
        Circle circle = new Circle(350, 200, 40);
        circle.setFill(imagePattern);
        root.getChildren().add(circle);

        Label label = new Label(account.getUsername());
        label.setTextFill(Color.WHITE);
        label.relocate(420, 160);
        label.setScaleY(1.6);
        label.setScaleX(1.7);
        label.setFont(font);
        root.getChildren().add(label);

        Label label1 = new Label("rank " + Integer.toString(MainMenu.getAccounts().indexOf(account) + 1));
        label1.setTextFill(Color.WHITE);
        label1.relocate(420, 200);
        label1.setScaleY(1.4);
        label1.setScaleX(1.5);
        label1.setFont(font);
        root.getChildren().add(label1);

        Image image4 = new Image(new FileInputStream("src/sample/profileCard.png"));
        ImageView imageView3 = new ImageView(image4);
        imageView3.relocate(170,300);
        root.getChildren().add(imageView3);

        Image image5 = new Image(new FileInputStream("src/sample/topCard.png"));
        ImageView imageView4 = new ImageView(image5);
        imageView4.relocate(210,320);
        root.getChildren().add(imageView4);

        imageView2.setOnMouseEntered(event -> {
            imageView2.setOpacity(1);
        });

        imageView2.setOnMouseExited(event -> {
            imageView2.setOpacity(0.7);
        });

        imageView2.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                clear(1);
                try {
                    inAccount();
                } catch (Exception e) {

                }
            }
        });

    }

    public void clear(int a) {
        for (int i = a; i < root.getChildren().size(); i++) {
            root.getChildren().remove(i);
            i--;
        }
    }

    public void shop() throws Exception {

        Font font = Font.loadFont(new FileInputStream("src/sample/font.ttf"), 20);

        Image backgroundShop = new Image(new FileInputStream("src/sample/shopBackGround.png"));
        ImageView backgroundShopView = new ImageView(backgroundShop);
        backgroundShopView.setFitWidth(1366);
        backgroundShopView.setFitHeight(768);
        root.getChildren().add(backgroundShopView);

        Image backButton = new Image(new FileInputStream("src/sample/backButton.png"));
        ImageView backButtonView = new ImageView(backButton);
        root.getChildren().add(backButtonView);

        TextField searchBox = new TextField();
        searchBox.setPromptText("Search");
        searchBox.setFocusTraversable(false);
        searchBox.setStyle("-fx-control-inner-background: #B1A7A7");
        searchBox.setScaleX(1.4);
        searchBox.relocate(150, 10);
        root.getChildren().add(searchBox);

        Image searchIcon = new Image(new FileInputStream("src/sample/search.png"));
        ImageView searchIconView = new ImageView(searchIcon);
        searchIconView.setScaleX(0.04);
        searchIconView.setScaleY(0.04);
        searchIconView.relocate(80, -232);
        root.getChildren().add(searchIconView);

        Image closeIcon = new Image(new FileInputStream("src/sample/closeIcon.png"));
        ImageView closeIconView = new ImageView(closeIcon);
        closeIconView.setScaleY(0.08);
        closeIconView.setScaleX(0.08);
        closeIconView.relocate(30, -283);

        Image changePageButton = new Image(new FileInputStream("src/sample/changePageButton.png"));
        ImageView changePageRightView = new ImageView(changePageButton);
        changePageRightView.relocate(876, 640);
        changePageRightView.setScaleX(0.7);
        changePageRightView.setScaleY(0.7);
        root.getChildren().add(changePageRightView);

        ImageView changePageLeftView = new ImageView(changePageButton);
        changePageLeftView.relocate(350, 640);
        changePageLeftView.setScaleX(0.7);
        changePageLeftView.setScaleY(0.7);
        root.getChildren().add(changePageLeftView);

        Label goRight = new Label(">>");
        goRight.setScaleX(1.5);
        goRight.setScaleY(3.5);
        goRight.setStyle("font-weight: bold;");
        goRight.relocate(944, 706);
        goRight.setTextFill(Color.BLACK);
        root.getChildren().add(goRight);

        Label goLeft = new Label("<<");
        goLeft.setScaleY(3.5);
        goLeft.setScaleX(1.5);
        goLeft.setStyle("font-weight: bold;");
        goLeft.relocate(415, 706);
        goLeft.setTextFill(Color.BLACK);
        root.getChildren().add(goLeft);

        Image customButton = new Image(new FileInputStream("src/sample/logout.png"));
        ImageView customButtonView = new ImageView(customButton);
        customButtonView.relocate(603, 690);
        customButtonView.setOpacity(0.7);
        root.getChildren().add(customButtonView);

        Label customLable = new Label("Custom Card");
        customLable.setTextFill(Color.WHITE);
        customLable.setFont(font);
        customLable.relocate(640, 700);
        root.getChildren().add(customLable);

        customButtonView.setOnMouseExited(event -> {
            customButtonView.setOpacity(0.7);
        });

        customButtonView.setOnMouseEntered(event -> {
            customButtonView.setOpacity(1);
        });

        customLable.setOnMouseEntered(event -> {
            customButtonView.setOpacity(1);
        });

        ArrayList<Group> allCardShow = new ArrayList<Group>();
        setCardInShow(allCardShow, page);
        setOpacityOfGroups(allCardShow);
        setBuyOrSellCardInShop(allCardShow);

        changePageRightView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundForFlipPage();
                audioPlayerForFlipPage.play();
                deleteGroup(allCardShow);
                allCardShow.clear();
                if (page * 8 >= Shop.getAllCards().size()) {
                    page = 1;
                } else {
                    page++;
                }
                setCardInShow(allCardShow, page);
                setOpacityOfGroups(allCardShow);
                setBuyOrSellCardInShop(allCardShow);
            }
        });

        goRight.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundForFlipPage();
                audioPlayerForFlipPage.play();
                deleteGroup(allCardShow);
                allCardShow.clear();
                if (page * 8 >= Shop.getAllCards().size()) {
                    page = 1;
                } else {
                    page++;
                }
                setCardInShow(allCardShow, page);
                setOpacityOfGroups(allCardShow);
                setBuyOrSellCardInShop(allCardShow);
            }
        });

        changePageLeftView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundForFlipPage();
                audioPlayerForFlipPage.play();
                deleteGroup(allCardShow);
                allCardShow.clear();
                if (page > 1) {
                    page--;
                } else {
                    if (Shop.getAllCards().size() % 8 == 0) {
                        page = Shop.getAllCards().size() / 8;
                    } else {
                        page = Shop.getAllCards().size() / 8 + 1;
                    }
                }
                setCardInShow(allCardShow, page);
                setOpacityOfGroups(allCardShow);
                setBuyOrSellCardInShop(allCardShow);
            }
        });

        goLeft.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundForFlipPage();
                audioPlayerForFlipPage.play();
                deleteGroup(allCardShow);
                allCardShow.clear();
                if (page > 1) {
                    page--;
                } else {
                    if (Shop.getAllCards().size() % 8 == 0) {
                        page = Shop.getAllCards().size() / 8;
                    } else {
                        page = Shop.getAllCards().size() / 8 + 1;
                    }
                }
                setCardInShow(allCardShow, page);
                setOpacityOfGroups(allCardShow);
                setBuyOrSellCardInShop(allCardShow);
            }
        });

        searchIconView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                root.getChildren().remove(searchIconView);
                root.getChildren().add(closeIconView);
                deleteGroup(allCardShow);
                allCardShow.clear();
                setSearchCard(allCardShow, searchBox.getText());
                setOpacityOfGroups(allCardShow);
                setBuyOrSellCardInShop(allCardShow);
            }
        });

        closeIconView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                root.getChildren().remove(closeIconView);
                root.getChildren().add(searchIconView);
                deleteGroup(allCardShow);
                allCardShow.clear();
                setCardInShow(allCardShow, page);
                setOpacityOfGroups(allCardShow);
                setBuyOrSellCardInShop(allCardShow);
            }
        });

        customButtonView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                try {
                    setSoundOfMouse();
                    deleteGroup(allCardShow);
                    customCardCreate();
                } catch (Exception e) {

                }
            }
        });

        customLable.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                try {
                    setSoundOfMouse();
                    deleteGroup(allCardShow);
                    customCardCreate();
                } catch (Exception e) {

                }
            }
        });

        backButtonView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                try {
                    inAccount();
                } catch (Exception e) {

                }
            }
        });

    }

    public void customCardCreate() throws Exception {

        Font font = Font.loadFont(new FileInputStream("src/sample/font.ttf"), 20);

        clear(1);
        Image image = new Image(new FileInputStream("src/sample/shopBackGround.png"));
        ImageView backGround = new ImageView(image);
        backGround.setFitWidth(1366);
        backGround.setFitHeight(768);
        root.getChildren().add(backGround);

        Image image1 = new Image(new FileInputStream("src/sample/backButton.png"));
        ImageView backButton = new ImageView(image1);
        root.getChildren().add(backButton);

        Label label3 = new Label("What card do you want to create");
        label3.setTextFill(Color.WHITE);
        label3.setScaleY(2.5);
        label3.setScaleX(2.5);
        label3.relocate(450, 150);
        label3.setFont(font);
        root.getChildren().add(label3);

        Label label = new Label("Hero");
        label.setFont(font);
        label.setTextFill(Color.WHITE);
        label.setOpacity(0.7);
        label.setScaleX(2.5);
        label.setScaleY(2);
        label.relocate(350, 300);
        root.getChildren().add(label);

        Label label1 = new Label("Minion");
        label1.setFont(font);
        label1.setTextFill(Color.WHITE);
        label1.setOpacity(0.7);
        label1.setScaleX(2.5);
        label1.setScaleY(2);
        label1.relocate(350, 400);
        root.getChildren().add(label1);

        Label label2 = new Label("Spell");
        label2.setFont(font);
        label2.setTextFill(Color.WHITE);
        label2.setOpacity(0.7);
        label2.setScaleX(2.5);
        label2.setScaleY(2);
        label2.relocate(350, 500);
        root.getChildren().add(label2);

        label.setOnMouseEntered(event -> {
            label.setOpacity(1);
        });

        label.setOnMouseExited(event -> {
            label.setOpacity(0.7);
        });

        label1.setOnMouseEntered(event -> {
            label1.setOpacity(1);
        });

        label1.setOnMouseExited(event -> {
            label1.setOpacity(0.7);
        });

        label2.setOnMouseEntered(event -> {
            label2.setOpacity(1);
        });

        label2.setOnMouseExited(event -> {
            label2.setOpacity(0.7);
        });

        label.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                clear(1);
                try {
                    createHero();
                } catch (Exception e) {

                }
            }
        });

        label1.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                clear(1);
                try {
                    createMinion();
                } catch (Exception e) {

                }
            }
        });

        label2.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                clear(1);
                try {
                    createSpell();
                } catch (Exception e) {

                }
            }
        });

        backButton.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                clear(1);
                try {
                    shop();
                } catch (Exception e) {

                }
            }
        });

    }

    public void createSpell() throws Exception {


        Font font = Font.loadFont(new FileInputStream("src/sample/font.ttf"), 20);
        ArrayList<TextField> textFields = new ArrayList<>();

        Image image = new Image(new FileInputStream("src/sample/shopBackGround.png"));
        ImageView backGround = new ImageView(image);
        backGround.setFitWidth(1366);
        backGround.setFitHeight(768);
        root.getChildren().add(backGround);

        Image image1 = new Image(new FileInputStream("src/sample/backButton.png"));
        ImageView backButton = new ImageView(image1);
        root.getChildren().add(backButton);

        group = getCardForMinion(310, 250, 0, 0, 0, "", "", "", "Spell", "", "");
        root.getChildren().add(group);


        Image image2 = new Image(new FileInputStream("src/sample/logout.png"));
        ImageView next = new ImageView(image2);
        next.setOpacity(0.8);
        next.relocate(580, 490);
        root.getChildren().add(next);

        Label label = new Label("Next");
        label.setTextFill(Color.WHITE);
        label.setFont(font);
        label.setScaleX(1.5);
        label.setScaleY(1.5);
        label.relocate(640, 497);
        root.getChildren().add(label);

        TextField name = new TextField();
        name.setPromptText("Name");
        name.setFocusTraversable(false);
        name.relocate(580, 250);
        name.setStyle("-fx-control-inner-background: #8C8484");
        root.getChildren().add(name);
        textFields.add(name);

        TextField cost = new TextField();
        cost.setPromptText("Cost");
        cost.setFocusTraversable(false);
        cost.relocate(580, 310);
        cost.setStyle("-fx-control-inner-background: #8C8484");
        root.getChildren().add(cost);
        textFields.add(cost);

        TextField buffs = new TextField();
        buffs.setPromptText("Buffs");
        buffs.setFocusTraversable(false);
        buffs.relocate(580, 370);
        buffs.setStyle("-fx-control-inner-background: #8C8484");
        root.getChildren().add(buffs);
        textFields.add(buffs);

        TextField MP = new TextField();
        MP.setPromptText("MP");
        MP.setFocusTraversable(false);
        MP.relocate(580, 430);
        MP.setStyle("-fx-control-inner-background: #8C8484");
        root.getChildren().add(MP);
        textFields.add(MP);

        for (TextField textField : textFields) {
            textField.setOnKeyPressed(event -> {
                root.getChildren().remove(group);
                group = getCardForMinion(310, 250, 0, 0, MP.getText().matches("[\\d]+") ? Integer.parseInt(MP.getText()) : 0, "", name.getText(), "", "Spell", "", "");
                root.getChildren().add(group);
            });
        }

        next.setOnMouseEntered(event -> {
            next.setOpacity(1);
        });

        next.setOnMouseExited(event -> {
            next.setOpacity(0.8);
        });

        label.setOnMouseEntered(event -> {
            next.setOpacity(1);
        });

        backButton.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                clear(1);
                try {
                    customCardCreate();
                } catch (Exception e) {

                }
            }
        });

        next.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                root.getChildren().removeAll(backButton, next, label, name, cost, MP, buffs);
                try {
                    finalCreateSpell(name.getText(), cost.getText().matches("[\\d]+") ? Integer.parseInt(cost.getText()) : 0, MP.getText().matches("[\\d]+") ? Integer.parseInt(MP.getText()) : 0, buffs.getText());
                } catch (Exception e) {

                }
            }
        });

        label.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                root.getChildren().removeAll(backButton, next, label, name, cost, MP, buffs);
                try {
                    finalCreateSpell(name.getText(), cost.getText().matches("[\\d]+") ? Integer.parseInt(cost.getText()) : 0, MP.getText().matches("[\\d]+") ? Integer.parseInt(MP.getText()) : 0, buffs.getText());
                } catch (Exception e) {

                }
            }
        });


    }

    public void finalCreateSpell(String name, int cost, int MP, String buffs) throws Exception {

        Font font = Font.loadFont(new FileInputStream("src/sample/font.ttf"), 20);

        Image image1 = new Image(new FileInputStream("src/sample/backButton.png"));
        ImageView backButton = new ImageView(image1);
        root.getChildren().add(backButton);

        Image image2 = new Image(new FileInputStream("src/sample/logout.png"));
        ImageView next = new ImageView(image2);
        next.setOpacity(0.8);
        next.relocate(820, 490);
        root.getChildren().add(next);

        Label label1 = new Label("Create");
        label1.setTextFill(Color.WHITE);
        label1.setFont(font);
        label1.setScaleY(1.5);
        label1.setScaleX(1.5);
        label1.relocate(880, 497);
        root.getChildren().add(label1);


        Image image = new Image(new FileInputStream("src/image/Spell/custom1/idle.gif"));
        ImageView imageView = new ImageView(image);
        imageView.relocate(600, 345);
        imageView.setScaleX(2);
        imageView.setScaleY(2);
        imageView.setOpacity(0.7);
        root.getChildren().add(imageView);

        Image image3 = new Image(new FileInputStream("src/image/Spell/custom2/idle.gif"));
        ImageView imageView1 = new ImageView(image3);
        imageView1.relocate(760, 345);
        imageView1.setScaleY(2);
        imageView1.setScaleX(2);
        imageView1.setOpacity(0.7);
        root.getChildren().add(imageView1);

        Image image4 = new Image(new FileInputStream("src/image/Spell/custom3/idle.gif"));
        ImageView imageView2 = new ImageView(image4);
        imageView2.relocate(920, 345);
        imageView2.setScaleX(2);
        imageView2.setScaleY(2);
        imageView2.setOpacity(0.7);
        root.getChildren().add(imageView2);

        Image image5 = new Image(new FileInputStream("src/image/Spell/custom4/idle.gif"));
        ImageView imageView3 = new ImageView(image5);
        imageView3.relocate(1080, 345);
        imageView3.setScaleY(2);
        imageView3.setScaleX(2);
        imageView3.setOpacity(0.7);
        root.getChildren().add(imageView3);

        Image image6 = new Image(new FileInputStream("src/image/Spell/custom5/idle.gif"));
        ImageView imageView4 = new ImageView(image6);
        imageView4.relocate(1240, 345);
        imageView4.setScaleX(2);
        imageView4.setScaleY(2);
        imageView4.setOpacity(0.7);
        root.getChildren().add(imageView4);


        label1.setOnMouseEntered(event -> {
            next.setOpacity(1);
        });

        next.setOnMouseEntered(event -> {
            next.setOpacity(1);
        });

        next.setOnMouseExited(event -> {
            next.setOpacity(0.8);
        });

        imageView.setOnMouseExited(event -> {
            imageView.setOpacity(0.7);
        });

        imageView.setOnMouseEntered(event -> {
            imageView.setOpacity(1);
        });

        imageView1.setOnMouseExited(event -> {
            imageView1.setOpacity(0.7);
        });

        imageView1.setOnMouseEntered(event -> {
            imageView1.setOpacity(1);
        });

        imageView2.setOnMouseExited(event -> {
            imageView2.setOpacity(0.7);
        });

        imageView2.setOnMouseEntered(event -> {
            imageView2.setOpacity(1);
        });

        imageView3.setOnMouseExited(event -> {
            imageView3.setOpacity(0.7);
        });

        imageView3.setOnMouseEntered(event -> {
            imageView3.setOpacity(1);
        });

        imageView4.setOnMouseExited(event -> {
            imageView4.setOpacity(0.7);
        });

        imageView4.setOnMouseEntered(event -> {
            imageView4.setOpacity(1);
        });


        imageView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                root.getChildren().remove(group);
                group = getCardForMinion(310, 250, 0, 0, MP, "", name, "/idle.gif", "Spell", "", "custom1");
                root.getChildren().add(group);
                imageFolder = "custom1";
            }
        });

        imageView1.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                root.getChildren().remove(group);
                group = getCardForMinion(310, 250, 0, 0, MP, "", name, "/idle.gif", "Spell", "", "custom2");
                root.getChildren().add(group);
                imageFolder = "custom2";
            }
        });

        imageView2.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                root.getChildren().remove(group);
                group = getCardForMinion(310, 250, 0, 0, MP, "", name, "/idle.gif", "Spell", "", "custom3");
                root.getChildren().add(group);
                imageFolder = "custom3";
            }
        });

        imageView3.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                root.getChildren().remove(group);
                group = getCardForMinion(310, 250, 0, 0, MP, "", name, "/idle.gif", "Spell", "", "custom4");
                root.getChildren().add(group);
                imageFolder = "custom4";
            }
        });

        imageView4.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                root.getChildren().remove(group);
                group = getCardForMinion(310, 250, 0, 0, MP, "", name, "/idle.gif", "Spell", "", "custom5");
                root.getChildren().add(group);
                imageFolder = "custom5";
            }
        });

        next.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                clear(1);
                Spell spell = new Spell(name, "", "rectangle enemy MH", cost, MP, 1, 1, getBuffForSpellForCustomCard(buffs), false, imageFolder);
                Shop.getAllCards().add(spell);
                try {
                    customCardCreate();
                } catch (Exception e) {

                }
            }
        });

        label1.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                clear(1);
                Spell spell = new Spell(name, "", "rectangle enemy MH", cost, MP, 1, 1, getBuffForSpellForCustomCard(buffs), false, imageFolder);
                Shop.getAllCards().add(spell);
                try {
                    customCardCreate();
                } catch (Exception e) {

                }
            }
        });


        backButton.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                try {
                    clear(1);
                    createSpell();
                } catch (Exception e) {

                }
            }
        });


    }

    public ArrayList<Buff> getBuffForSpellForCustomCard(String string) {

        ArrayList<Buff> buffs = new ArrayList<Buff>();

        String[] strings = string.split(" ");

        for (int i = 0; i < strings.length; i++) {
            if (strings[i].matches("holy")) {
                Holy holy = new Holy(true, true, false, false, 1);
                buffs.add(holy);
            } else if (strings[i].matches("powerH")) {
                Power power = new Power(true, true, false, false, 1, 1, 0);
                buffs.add(power);
            } else if (strings[i].matches("powerP")) {
                Power power = new Power(true, true, false, false, 1, 0, 1);
                buffs.add(power);
            } else if (strings[i].matches("poison")) {
                Poison poison = new Poison(true, true, false, false, false, false, 1, 0, -1);
                buffs.add(poison);
            } else if (strings[i].matches("weaknessH")) {
                Weakness weakness = new Weakness(true, true, false, false, 1, -1, 0);
                buffs.add(weakness);
            } else if (strings[i].matches("weaknessP")) {
                Weakness weakness = new Weakness(true, true, false, false, 1, 0, -1);
                buffs.add(weakness);
            } else if (strings[i].matches("stun")) {
                Stun stun = new Stun(true, true, false, false, 1);
                buffs.add(stun);
            } else if (strings[i].matches("disarm")) {
                Disarm disarm = new Disarm(true, true, false, false, 1);
                buffs.add(disarm);
            }
        }
        return buffs;
    }

    public void createMinion() throws Exception {

        Font font = Font.loadFont(new FileInputStream("src/sample/font.ttf"), 20);
        ArrayList<TextField> textFields = new ArrayList<>();

        Image image = new Image(new FileInputStream("src/sample/shopBackGround.png"));
        ImageView backGround = new ImageView(image);
        backGround.setFitWidth(1366);
        backGround.setFitHeight(768);
        root.getChildren().add(backGround);

        Image image1 = new Image(new FileInputStream("src/sample/backButton.png"));
        ImageView backButton = new ImageView(image1);
        root.getChildren().add(backButton);

        group = getCardForMinion(310, 250, 0, 0, 0, "", "", "", "Minion", "", "");
        root.getChildren().add(group);

        Image image2 = new Image(new FileInputStream("src/sample/logout.png"));
        ImageView next = new ImageView(image2);
        next.setOpacity(0.8);
        next.relocate(820, 490);
        root.getChildren().add(next);

        Label label = new Label("Next");
        label.setTextFill(Color.WHITE);
        label.setFont(font);
        label.setScaleX(1.5);
        label.setScaleY(1.5);
        label.relocate(883, 497);
        root.getChildren().add(label);

        TextField name = new TextField();
        name.setPromptText("Name");
        name.setFocusTraversable(false);
        name.relocate(580, 250);
        name.setStyle("-fx-control-inner-background: #8C8484");
        root.getChildren().add(name);
        textFields.add(name);

        TextField cost = new TextField();
        cost.setPromptText("Cost");
        cost.setFocusTraversable(false);
        cost.relocate(580, 310);
        cost.setStyle("-fx-control-inner-background: #8C8484");
        root.getChildren().add(cost);
        textFields.add(cost);

        TextField HP = new TextField();
        HP.setPromptText("HP");
        HP.setFocusTraversable(false);
        HP.relocate(580, 370);
        HP.setStyle("-fx-control-inner-background: #8C8484");
        root.getChildren().add(HP);
        textFields.add(HP);

        TextField AP = new TextField();
        AP.setPromptText("AP");
        AP.setFocusTraversable(false);
        AP.relocate(580, 430);
        AP.setStyle("-fx-control-inner-background: #8C8484");
        root.getChildren().add(AP);
        textFields.add(AP);

        TextField typeAttack = new TextField();
        typeAttack.setPromptText("Type attack");
        typeAttack.setFocusTraversable(false);
        typeAttack.relocate(580, 490);
        typeAttack.setStyle("-fx-control-inner-background: #8C8484");
        root.getChildren().add(typeAttack);
        textFields.add(typeAttack);

        TextField attackRange = new TextField();
        attackRange.setPromptText("Attack range");
        attackRange.setFocusTraversable(false);
        attackRange.relocate(820, 250);
        attackRange.setStyle("-fx-control-inner-background: #8C8484");
        root.getChildren().add(attackRange);
        textFields.add(attackRange);

        TextField specialPower = new TextField();
        specialPower.setPromptText("Special power");
        specialPower.setFocusTraversable(false);
        specialPower.relocate(820, 310);
        specialPower.setStyle("-fx-control-inner-background: #8C8484");
        root.getChildren().add(specialPower);
        textFields.add(specialPower);

        TextField MP = new TextField();
        MP.setPromptText("MP");
        MP.setFocusTraversable(false);
        MP.relocate(820, 370);
        MP.setStyle("-fx-control-inner-background: #8C8484");
        root.getChildren().add(MP);
        textFields.add(MP);

        TextField activation = new TextField();
        activation.setPromptText("activation");
        activation.setFocusTraversable(false);
        activation.relocate(820, 430);
        activation.setStyle("-fx-control-inner-background: #8C8484");
        root.getChildren().add(activation);
        textFields.add(activation);

        next.setOnMouseEntered(event -> {
            next.setOpacity(1);
        });

        next.setOnMouseExited(event -> {
            next.setOpacity(0.8);
        });

        label.setOnMouseEntered(event -> {
            next.setOpacity(1);
        });

        for (TextField textField : textFields) {
            textField.setOnKeyPressed(event -> {
                root.getChildren().remove(group);
                if (Shop.getCard(specialPower.getText()) instanceof Spell) {
                    group = getCardForMinion(310, 250, AP.getText().matches("[\\d]+") ? Integer.parseInt(AP.getText()) : 0, HP.getText().matches("[\\d]+") ? Integer.parseInt(HP.getText()) : 0, MP.getText().matches("[\\d]+") ? Integer.parseInt(MP.getText()) : 0, ((Spell) Shop.getCard(specialPower.getText())).getDesc(), name.getText(), "", "Minion", typeAttack.getText(), "");
                } else {
                    group = getCardForMinion(310, 250, AP.getText().matches("[\\d]+") ? Integer.parseInt(AP.getText()) : 0, HP.getText().matches("[\\d]+") ? Integer.parseInt(HP.getText()) : 0, MP.getText().matches("[\\d]+") ? Integer.parseInt(MP.getText()) : 0, "", name.getText(), "", "Minion", typeAttack.getText(), "");
                }
                root.getChildren().add(group);
            });
        }

        next.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                root.getChildren().removeAll(backButton, next, label, name, cost, HP, MP, AP, activation, specialPower, attackRange, typeAttack);
                try {
                    finalCreateMinion(name.getText(), cost.getText().matches("[\\d]+") ? Integer.parseInt(cost.getText()) : 0, HP.getText().matches("[\\d]+") ? Integer.parseInt(HP.getText()) : 0, AP.getText().matches("[\\d]+") ? Integer.parseInt(AP.getText()) : 0, typeAttack.getText(), attackRange.getText().matches("[\\d]+") ? Integer.parseInt(attackRange.getText()) : 0, specialPower.getText(), MP.getText().matches("[\\d]+") ? Integer.parseInt(MP.getText()) : 0, activation.getText());
                } catch (Exception e) {

                }
            }
        });

        label.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                root.getChildren().removeAll(backButton, next, label, name, cost, HP, MP, AP, activation, specialPower, attackRange, typeAttack);
                try {
                    finalCreateMinion(name.getText(), cost.getText().matches("[\\d]+") ? Integer.parseInt(cost.getText()) : 0, HP.getText().matches("[\\d]+") ? Integer.parseInt(HP.getText()) : 0, AP.getText().matches("[\\d]+") ? Integer.parseInt(AP.getText()) : 0, typeAttack.getText(), attackRange.getText().matches("[\\d]+") ? Integer.parseInt(attackRange.getText()) : 0, specialPower.getText(), MP.getText().matches("[\\d]+") ? Integer.parseInt(MP.getText()) : 0, activation.getText());
                } catch (Exception e) {

                }
            }
        });

        backButton.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                clear(1);
                try {
                    customCardCreate();
                } catch (Exception e) {

                }
            }
        });

    }

    public void createHero() throws Exception {

        Font font = Font.loadFont(new FileInputStream("src/sample/font.ttf"), 20);
        ArrayList<TextField> textFields = new ArrayList<>();

        Image image = new Image(new FileInputStream("src/sample/shopBackGround.png"));
        ImageView backGround = new ImageView(image);
        backGround.setFitWidth(1366);
        backGround.setFitHeight(768);
        root.getChildren().add(backGround);

        Image image1 = new Image(new FileInputStream("src/sample/backButton.png"));
        ImageView backButton = new ImageView(image1);
        root.getChildren().add(backButton);

        Image image2 = new Image(new FileInputStream("src/sample/logout.png"));
        ImageView next = new ImageView(image2);
        next.setOpacity(0.8);
        next.relocate(820, 490);
        root.getChildren().add(next);

        Label label = new Label("Next");
        label.setTextFill(Color.WHITE);
        label.setFont(font);
        label.setScaleX(1.5);
        label.setScaleY(1.5);
        label.relocate(883, 497);
        root.getChildren().add(label);

        TextField name = new TextField();
        name.setPromptText("Name");
        name.setFocusTraversable(false);
        name.relocate(580, 250);
        name.setStyle("-fx-control-inner-background: #8C8484");
        root.getChildren().add(name);
        textFields.add(name);

        TextField cost = new TextField();
        cost.setPromptText("Cost");
        cost.setFocusTraversable(false);
        cost.relocate(580, 310);
        cost.setStyle("-fx-control-inner-background: #8C8484");
        root.getChildren().add(cost);
        textFields.add(cost);

        TextField HP = new TextField();
        HP.setPromptText("HP");
        HP.setFocusTraversable(false);
        HP.relocate(580, 370);
        HP.setStyle("-fx-control-inner-background: #8C8484");
        root.getChildren().add(HP);
        textFields.add(HP);

        TextField AP = new TextField();
        AP.setPromptText("AP");
        AP.setFocusTraversable(false);
        AP.relocate(580, 430);
        AP.setStyle("-fx-control-inner-background: #8C8484");
        root.getChildren().add(AP);
        textFields.add(AP);

        TextField typeAttack = new TextField();
        typeAttack.setPromptText("Type attack");
        typeAttack.setFocusTraversable(false);
        typeAttack.relocate(580, 490);
        typeAttack.setStyle("-fx-control-inner-background: #8C8484");
        root.getChildren().add(typeAttack);
        textFields.add(typeAttack);

        TextField attackRange = new TextField();
        attackRange.setPromptText("Attack range");
        attackRange.setFocusTraversable(false);
        attackRange.relocate(820, 250);
        attackRange.setStyle("-fx-control-inner-background: #8C8484");
        root.getChildren().add(attackRange);
        textFields.add(attackRange);

        TextField specialPower = new TextField();
        specialPower.setPromptText("Special power");
        specialPower.setFocusTraversable(false);
        specialPower.relocate(820, 310);
        specialPower.setStyle("-fx-control-inner-background: #8C8484");
        root.getChildren().add(specialPower);
        textFields.add(specialPower);

        TextField MP = new TextField();
        MP.setPromptText("MP");
        MP.setFocusTraversable(false);
        MP.relocate(820, 370);
        MP.setStyle("-fx-control-inner-background: #8C8484");
        root.getChildren().add(MP);
        textFields.add(MP);

        TextField cooldown = new TextField();
        cooldown.setPromptText("cooldown");
        cooldown.setFocusTraversable(false);
        cooldown.relocate(820, 430);
        cooldown.setStyle("-fx-control-inner-background: #8C8484");
        root.getChildren().add(cooldown);
        textFields.add(cooldown);

        group = getCardForMinion(310, 250, 0, 0, 0, "", "", "", "Hero", "", "");
        root.getChildren().add(group);

        next.setOnMouseEntered(event -> {
            next.setOpacity(1);
        });

        next.setOnMouseExited(event -> {
            next.setOpacity(0.8);
        });

        label.setOnMouseEntered(event -> {
            next.setOpacity(1);
        });

        for (TextField textField : textFields) {
            textField.setOnKeyPressed(event -> {
                root.getChildren().remove(group);
                if (Shop.getCard(specialPower.getText()) instanceof Spell) {
                    group = getCardForMinion(310, 250, AP.getText().matches("[\\d]+") ? Integer.parseInt(AP.getText()) : 0, HP.getText().matches("[\\d]+") ? Integer.parseInt(HP.getText()) : 0, 0, ((Spell) Shop.getCard(specialPower.getText())).getDesc(), name.getText(), "", "Hero", typeAttack.getText(), "");
                } else {
                    group = getCardForMinion(310, 250, AP.getText().matches("[\\d]+") ? Integer.parseInt(AP.getText()) : 0, HP.getText().matches("[\\d]+") ? Integer.parseInt(HP.getText()) : 0, 0, "", name.getText(), "", "Hero", typeAttack.getText(), "");
                }
                root.getChildren().add(group);
            });
        }

        next.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                root.getChildren().removeAll(backButton, next, label, name, cost, HP, MP, AP, cooldown, specialPower, attackRange, typeAttack);
                try {
                    finalCreateHero(name.getText(), cost.getText().matches("[\\d]+") ? Integer.parseInt(cost.getText()) : 0, HP.getText().matches("[\\d]+") ? Integer.parseInt(HP.getText()) : 0, AP.getText().matches("[\\d]+") ? Integer.parseInt(AP.getText()) : 0, typeAttack.getText(), attackRange.getText().matches("[\\d]+") ? Integer.parseInt(attackRange.getText()) : 0, specialPower.getText(), MP.getText().matches("[\\d]+") ? Integer.parseInt(MP.getText()) : 0, cooldown.getText().matches("[\\d]+") ? Integer.parseInt(cooldown.getText()) : 0);
                } catch (Exception e) {

                }
            }
        });

        label.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                root.getChildren().removeAll(backButton, next, label, name, cost, HP, MP, AP, cooldown, specialPower, attackRange, typeAttack);
                try {
                    finalCreateHero(name.getText(), cost.getText().matches("[\\d]+") ? Integer.parseInt(cost.getText()) : 0, HP.getText().matches("[\\d]+") ? Integer.parseInt(HP.getText()) : 0, AP.getText().matches("[\\d]+") ? Integer.parseInt(AP.getText()) : 0, typeAttack.getText(), attackRange.getText().matches("[\\d]+") ? Integer.parseInt(attackRange.getText()) : 0, specialPower.getText(), MP.getText().matches("[\\d]+") ? Integer.parseInt(MP.getText()) : 0, cooldown.getText().matches("[\\d]+") ? Integer.parseInt(cooldown.getText()) : 0);
                } catch (Exception e) {

                }
            }
        });

        backButton.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                clear(1);
                try {
                    customCardCreate();
                } catch (Exception e) {

                }
            }
        });


    }

    public void finalCreateMinion(String name, int cost, int HP, int AP, String attackType, int attackRange, String specialPower, int MP, String activation) throws Exception {

        Font font = Font.loadFont(new FileInputStream("src/sample/font.ttf"), 20);

        Image image1 = new Image(new FileInputStream("src/sample/backButton.png"));
        ImageView backButton = new ImageView(image1);
        root.getChildren().add(backButton);

        Image image2 = new Image(new FileInputStream("src/sample/logout.png"));
        ImageView next = new ImageView(image2);
        next.setOpacity(0.8);
        next.relocate(820, 490);
        root.getChildren().add(next);

        Label label1 = new Label("Create");
        label1.setTextFill(Color.WHITE);
        label1.setFont(font);
        label1.setScaleY(1.5);
        label1.setScaleX(1.5);
        label1.relocate(880, 497);
        root.getChildren().add(label1);

        Image image = new Image(new FileInputStream("src/image/Minion/custom1/idle.gif"));
        ImageView imageView = new ImageView(image);
        imageView.relocate(530, 260);
        imageView.setScaleX(2);
        imageView.setScaleY(2);
        imageView.setOpacity(0.7);
        root.getChildren().add(imageView);

        Image image3 = new Image(new FileInputStream("src/image/Minion/custom2/idle.gif"));
        ImageView imageView1 = new ImageView(image3);
        imageView1.relocate(740, 335);
        imageView1.setScaleY(2);
        imageView1.setScaleX(2);
        imageView1.setOpacity(0.7);
        root.getChildren().add(imageView1);

        Image image4 = new Image(new FileInputStream("src/image/Minion/custom3/idle.gif"));
        ImageView imageView2 = new ImageView(image4);
        imageView2.relocate(880, 260);
        imageView2.setScaleX(2);
        imageView2.setScaleY(2);
        imageView2.setOpacity(0.7);
        root.getChildren().add(imageView2);

        Image image5 = new Image(new FileInputStream("src/image/Minion/custom4/idle.gif"));
        ImageView imageView3 = new ImageView(image5);
        imageView3.relocate(1060, 250);
        imageView3.setScaleY(2);
        imageView3.setScaleX(2);
        imageView3.setOpacity(0.7);
        root.getChildren().add(imageView3);

        Image image6 = new Image(new FileInputStream("src/image/Minion/custom5/idle.gif"));
        ImageView imageView4 = new ImageView(image6);
        imageView4.relocate(1200, 300);
        imageView4.setScaleX(2);
        imageView4.setScaleY(2);
        imageView4.setOpacity(0.7);
        root.getChildren().add(imageView4);

        label1.setOnMouseEntered(event -> {
            next.setOpacity(1);
        });

        next.setOnMouseEntered(event -> {
            next.setOpacity(1);
        });

        next.setOnMouseExited(event -> {
            next.setOpacity(0.8);
        });

        imageView.setOnMouseExited(event -> {
            imageView.setOpacity(0.7);
        });

        imageView.setOnMouseEntered(event -> {
            imageView.setOpacity(1);
        });

        imageView1.setOnMouseExited(event -> {
            imageView1.setOpacity(0.7);
        });

        imageView1.setOnMouseEntered(event -> {
            imageView1.setOpacity(1);
        });

        imageView2.setOnMouseExited(event -> {
            imageView2.setOpacity(0.7);
        });

        imageView2.setOnMouseEntered(event -> {
            imageView2.setOpacity(1);
        });

        imageView3.setOnMouseExited(event -> {
            imageView3.setOpacity(0.7);
        });

        imageView3.setOnMouseEntered(event -> {
            imageView3.setOpacity(1);
        });

        imageView4.setOnMouseExited(event -> {
            imageView4.setOpacity(0.7);
        });

        imageView4.setOnMouseEntered(event -> {
            imageView4.setOpacity(1);
        });


        imageView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                root.getChildren().remove(group);
                group = getCardForMinion(310, 250, AP, HP, MP, Shop.getCard(specialPower) instanceof Spell ? ((Spell) Shop.getCard(specialPower)).getDesc() : "", name, "/idle.gif", "Minion", attackType, "custom1");
                root.getChildren().add(group);
                imageFolder = "custom1";
            }
        });

        imageView1.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                root.getChildren().remove(group);
                group = getCardForMinion(310, 250, AP, HP, MP, Shop.getCard(specialPower) instanceof Spell ? ((Spell) Shop.getCard(specialPower)).getDesc() : "", name, "/idle.gif", "Minion", attackType, "custom2");
                root.getChildren().add(group);
                imageFolder = "custom2";
            }
        });

        imageView2.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                root.getChildren().remove(group);
                group = getCardForMinion(310, 250, AP, HP, MP, Shop.getCard(specialPower) instanceof Spell ? ((Spell) Shop.getCard(specialPower)).getDesc() : "", name, "/idle.gif", "Minion", attackType, "custom3");
                root.getChildren().add(group);
                imageFolder = "custom3";
            }
        });

        imageView3.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                root.getChildren().remove(group);
                group = getCardForMinion(310, 250, AP, HP, MP, Shop.getCard(specialPower) instanceof Spell ? ((Spell) Shop.getCard(specialPower)).getDesc() : "", name, "/idle.gif", "Minion", attackType, "custom4");
                root.getChildren().add(group);
                imageFolder = "custom4";
            }
        });

        imageView4.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                root.getChildren().remove(group);
                group = getCardForMinion(310, 250, AP, HP, MP, Shop.getCard(specialPower) instanceof Spell ? ((Spell) Shop.getCard(specialPower)).getDesc() : "", name, "/idle.gif", "Minion", attackType, "custom5");
                root.getChildren().add(group);
                imageFolder = "custom5";
            }
        });


        next.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                clear(1);
                ArrayList<Buff> buffs = new ArrayList<>();
                if (Shop.getCard(specialPower) instanceof Spell) {
                    for (Buff buff : Shop.getCard(specialPower).getBuffs()) {
                        buffs.add(buff.copyBuff());
                    }
                }
                Minion minion = new Minion(name, attackType, cost, MP, HP, AP, attackRange, buffs, activation, "attackCell MH", specialPower, false, imageFolder);
                Shop.getAllCards().add(minion);
                try {
                    customCardCreate();
                } catch (Exception e) {

                }
            }
        });

        label1.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                clear(1);
                ArrayList<Buff> buffs = new ArrayList<>();
                if (Shop.getCard(specialPower) instanceof Spell) {
                    for (Buff buff : Shop.getCard(specialPower).getBuffs()) {
                        buffs.add(buff.copyBuff());
                    }
                }
                Minion minion = new Minion(name, attackType, cost, MP, HP, AP, attackRange, buffs, activation, "attackCell MH", specialPower, false, imageFolder);
                Shop.getAllCards().add(minion);
                try {
                    customCardCreate();
                } catch (Exception e) {

                }
            }
        });


        backButton.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                try {
                    clear(1);
                    createMinion();
                } catch (Exception e) {

                }
            }
        });

    }


    public void finalCreateHero(String name, int cost, int HP, int AP, String attackType, int attackRange, String specialPower, int MP, int cooldown) throws Exception {

        Font font = Font.loadFont(new FileInputStream("src/sample/font.ttf"), 20);

        Image image1 = new Image(new FileInputStream("src/sample/backButton.png"));
        ImageView backButton = new ImageView(image1);
        root.getChildren().add(backButton);

        Image image2 = new Image(new FileInputStream("src/sample/logout.png"));
        ImageView next = new ImageView(image2);
        next.setOpacity(0.8);
        next.relocate(820, 490);
        root.getChildren().add(next);

        Label label1 = new Label("Create");
        label1.setTextFill(Color.WHITE);
        label1.setFont(font);
        label1.setScaleY(1.5);
        label1.setScaleX(1.5);
        label1.relocate(880, 497);
        root.getChildren().add(label1);

        Image image = new Image(new FileInputStream("src/image/Hero/custom1/idle.gif"));
        ImageView imageView = new ImageView(image);
        imageView.relocate(530, 260);
        imageView.setScaleX(2);
        imageView.setScaleY(2);
        imageView.setOpacity(0.7);
        root.getChildren().add(imageView);

        Image image3 = new Image(new FileInputStream("src/image/Hero/custom2/idle.gif"));
        ImageView imageView1 = new ImageView(image3);
        imageView1.relocate(740, 335);
        imageView1.setScaleY(2);
        imageView1.setScaleX(2);
        imageView1.setOpacity(0.7);
        root.getChildren().add(imageView1);

        Image image4 = new Image(new FileInputStream("src/image/Hero/custom3/idle.gif"));
        ImageView imageView2 = new ImageView(image4);
        imageView2.relocate(880, 260);
        imageView2.setScaleX(2);
        imageView2.setScaleY(2);
        imageView2.setOpacity(0.7);
        root.getChildren().add(imageView2);

        Image image5 = new Image(new FileInputStream("src/image/Hero/custom4/idle.gif"));
        ImageView imageView3 = new ImageView(image5);
        imageView3.relocate(1060, 250);
        imageView3.setScaleY(2);
        imageView3.setScaleX(2);
        imageView3.setOpacity(0.7);
        root.getChildren().add(imageView3);

        Image image6 = new Image(new FileInputStream("src/image/Hero/custom5/idle.gif"));
        ImageView imageView4 = new ImageView(image6);
        imageView4.relocate(1200, 300);
        imageView4.setScaleX(2);
        imageView4.setScaleY(2);
        imageView4.setOpacity(0.7);
        root.getChildren().add(imageView4);

        backButton.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                try {
                    clear(1);
                    createHero();
                } catch (Exception e) {

                }
            }
        });

        label1.setOnMouseEntered(event -> {
            next.setOpacity(1);
        });

        next.setOnMouseEntered(event -> {
            next.setOpacity(1);
        });

        next.setOnMouseExited(event -> {
            next.setOpacity(0.8);
        });

        imageView.setOnMouseExited(event -> {
            imageView.setOpacity(0.7);
        });

        imageView.setOnMouseEntered(event -> {
            imageView.setOpacity(1);
        });

        imageView1.setOnMouseExited(event -> {
            imageView1.setOpacity(0.7);
        });

        imageView1.setOnMouseEntered(event -> {
            imageView1.setOpacity(1);
        });

        imageView2.setOnMouseExited(event -> {
            imageView2.setOpacity(0.7);
        });

        imageView2.setOnMouseEntered(event -> {
            imageView2.setOpacity(1);
        });

        imageView3.setOnMouseExited(event -> {
            imageView3.setOpacity(0.7);
        });

        imageView3.setOnMouseEntered(event -> {
            imageView3.setOpacity(1);
        });

        imageView4.setOnMouseExited(event -> {
            imageView4.setOpacity(0.7);
        });

        imageView4.setOnMouseEntered(event -> {
            imageView4.setOpacity(1);
        });

        imageView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                root.getChildren().remove(group);
                group = getCardForMinion(310, 250, AP, HP, MP, Shop.getCard(specialPower) instanceof Spell ? ((Spell) Shop.getCard(specialPower)).getDesc() : "", name, "/idle.gif", "Hero", attackType, "custom1");
                root.getChildren().add(group);
                imageFolder = "custom1";
            }
        });

        imageView1.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                root.getChildren().remove(group);
                group = getCardForMinion(310, 250, AP, HP, MP, Shop.getCard(specialPower) instanceof Spell ? ((Spell) Shop.getCard(specialPower)).getDesc() : "", name, "/idle.gif", "Hero", attackType, "custom2");
                root.getChildren().add(group);
                imageFolder = "custom2";
            }
        });

        imageView2.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                root.getChildren().remove(group);
                group = getCardForMinion(310, 250, AP, HP, MP, Shop.getCard(specialPower) instanceof Spell ? ((Spell) Shop.getCard(specialPower)).getDesc() : "", name, "/idle.gif", "Hero", attackType, "custom3");
                root.getChildren().add(group);
                imageFolder = "custom3";
            }
        });

        imageView3.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                root.getChildren().remove(group);
                group = getCardForMinion(310, 250, AP, HP, MP, Shop.getCard(specialPower) instanceof Spell ? ((Spell) Shop.getCard(specialPower)).getDesc() : "", name, "/idle.gif", "Hero", attackType, "custom4");
                root.getChildren().add(group);
                imageFolder = "custom4";
            }
        });

        imageView4.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                root.getChildren().remove(group);
                group = getCardForMinion(310, 250, AP, HP, MP, Shop.getCard(specialPower) instanceof Spell ? ((Spell) Shop.getCard(specialPower)).getDesc() : "", name, "/idle.gif", "Hero", attackType, "custom5");
                root.getChildren().add(group);
                imageFolder = "custom5";
            }
        });

        next.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                setSoundOfMouse();
                clear(1);
                ArrayList<Buff> buffs = new ArrayList<>();
                if (Shop.getCard(specialPower) instanceof Spell) {
                    for (Buff buff : Shop.getCard(specialPower).getBuffs()) {
                        buffs.add(buff.copyBuff());
                    }
                }
                Hero hero = new Hero(name, attackType, cost, MP, HP, AP, attackRange, cooldown, Shop.getCard(specialPower) instanceof Spell ? ((Spell) Shop.getCard(specialPower)).getDesc() : "Don't have special power", buffs, "one enemy", true, false, false, imageFolder);
                Shop.getAllCards().add(hero);
                try {
                    customCardCreate();
                } catch (Exception e) {

                }
            }
        });

        label1.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    setSoundOfMouse();
                    clear(1);
                    ArrayList<Buff> buffs = new ArrayList<>();
                    if (Shop.getCard(specialPower) instanceof Spell) {
                        for (Buff buff : Shop.getCard(specialPower).getBuffs()) {
                            buffs.add(buff);
                        }
                    }
                    Hero hero = new Hero(name, attackType, cost, MP, HP, AP, attackRange, cooldown, Shop.getCard(specialPower) instanceof Spell ? ((Spell) Shop.getCard(specialPower)).getDesc() : "Don't have special power", buffs, "", true, false, false, imageFolder);
                    Shop.getAllCards().add(hero);
                    try {
                        customCardCreate();
                    } catch (Exception e) {

                    }
                }
            }
        });

    }

    public void setSearchCard(ArrayList<Group> groups, String name) {

        Card card = Shop.getCard(name);
        Group group = new Group();

        try {
            Font font = Font.loadFont(new FileInputStream("src/sample/font.ttf"), 20);

            if (card == null) {
                Label label = new Label("Don't exist this card");
                label.setTextFill(Color.WHITE);
                label.setScaleY(3);
                label.setScaleX(3);
                label.relocate(610, 370);
                label.setFont(font);
                group.getChildren().add(label);
                groups.add(group);
                root.getChildren().add(group);
            } else {
                if (card instanceof Spell) {
                    group = getCardForMinion(115, 65, 0, 0, card.MP, ((Spell) card).getDesc(), card.name, "/idle.gif", "Spell", "", card.imageName);
                    groups.add(group);
                    root.getChildren().add(group);
                } else if (card instanceof Minion) {
                    group = getCardForMinion(115, 65, ((Minion) card).getAP(), ((Minion) card).getHP(), card.MP, ((Minion) card).getSpecialPower(), card.name, "/idle.gif", "Minion", ((Minion) card).getClas(), card.imageName);
                    groups.add(group);
                    root.getChildren().add(group);
                } else if (card instanceof Hero) {
                    group = getCardForMinion(115, 65, ((Hero) card).getAP(), ((Hero) card).getHP(), card.MP, ((Hero) card).getSpecialPower(), card.name, "/idle.gif", "Hero", ((Hero) card).getClas(), card.imageName);
                    groups.add(group);
                    root.getChildren().add(group);
                } else {
                    group = getCardForMinion(115, 65, 0, 0, 0, ((Item) card).getDesc(), card.name, "/idle.gif", "Item", "", card.imageName);
                    groups.add(group);
                    root.getChildren().add(group);
                }
            }
        } catch (Exception e) {

        }

    }

    public void deleteGroup(ArrayList<Group> groups) {
        for (Group group : groups) {
            root.getChildren().remove(group);
        }
    }

    public void setCardInShow(ArrayList<Group> groups, int page) {
        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 2; j++) {

                if ((page - 1) * 8 + i * 2 + j == Shop.getAllCards().size()) {
                    return;
                }
                Card card = Shop.getAllCards().get((page - 1) * 8 + i * 2 + j);
                if (card instanceof Spell) {
                    Group group = getCardForMinion(115 + i * 300, 65 + j * 320, 0, 0, card.MP, ((Spell) card).getDesc(), card.name, "/idle.gif", "Spell", "", card.imageName);
                    groups.add(group);
                    root.getChildren().add(group);
                } else if (card instanceof Minion) {
                    Group group = getCardForMinion(115 + i * 300, 65 + j * 320, ((Minion) card).getAP(), ((Minion) card).getHP(), card.MP, ((Minion) card).getSpecialPower(), card.name, "/idle.gif", "Minion", ((Minion) card).getClas(), card.imageName);
                    groups.add(group);
                    root.getChildren().add(group);
                } else if (card instanceof Hero) {
                    Group group = getCardForMinion(115 + i * 300, 65 + j * 320, ((Hero) card).getAP(), ((Hero) card).getHP(), card.MP, ((Hero) card).getSpecialPower(), card.name, "/idle.gif", "Hero", ((Hero) card).getClas(), card.imageName);
                    groups.add(group);
                    root.getChildren().add(group);
                } else {
                    Group group = getCardForMinion(115 + i * 300, 65 + j * 320, 0, 0, 0, ((Item) card).getDesc(), card.name, "/idle.gif", "Item", "", card.imageName);
                    groups.add(group);
                    root.getChildren().add(group);
                }

            }

        }

    }

    public void deleteImageFromLable(double x, double y) {
        for (int i = 0; i < root.getChildren().size(); i++) {
            if (root.getChildren().get(i) instanceof ImageView) {
                if (((ImageView) root.getChildren().get(i)).getLayoutX() == x && ((ImageView) root.getChildren().get(i)).getLayoutY() == y) {
                    root.getChildren().remove(i);
                    i--;
                }
            }
        }
    }

    public void setTriangleAsCursor(int x) {

        root.getChildren().remove(triangle);

        if (x == 0) {
            triangle = new Polygon();
            triangle.getPoints().addAll(1075.0, 120.0, 1090.0, 120.0, 1082.5, 133.0);
            triangle.setFill(Color.YELLOW);
        } else {
            triangle = new Polygon();
            triangle.getPoints().addAll(1179.0, 120.0, 1194.0, 120.0, 1186.5, 133.0);
            triangle.setFill(Color.YELLOW);
        }
        root.getChildren().add(triangle);

    }

    public void setMouse() throws Exception {
        Image mouse = new Image(new FileInputStream("src/sample/Mouse.png"));
        ImageCursor cursor = new ImageCursor(mouse);
        scene.setCursor(cursor);
    }

    public void setBuyOrSellCardInShop(ArrayList<Group> groups) {

        for (Group group : groups) {
            group.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    setSoundOfMouse();
                    String nameOfCard = ((Label) ((StackPane) group.getChildren().get(2)).getChildren().get(1)).getText();
                    int a = Shop.buy(nameOfCard, account);
                    if (a == 1) {
                        errorForSameUserName("You don't have enough money", true, 600, 350, 2.5, 2.5, 700);
                    } else if (a == 2) {
                        errorForSameUserName("You can't buy more item", true, 630, 350, 2.5, 2.5, 700);
                    } else {
                        errorForSameUserName("Successful shopping", true, 630, 350, 2.5, 2.5, 700);
                    }
                }
            });
        }
    }

    public void setOpacityOfGroups(ArrayList<Group> groups) {
        for (Group group : groups) {
            group.setOnMouseEntered(event -> {
                group.setOpacity(1);
            });
            group.setOnMouseExited(event -> {
                group.setOpacity(0.85);
            });
        }
    }

    public Group getCardForMinion(int x, int y, int AP, int HP, int MP, String desc, String name, String imageName, String type, String typeAttack, String imageFolder) {

        Group group = new Group();
        group.setOpacity(0.85);

        try {

            Image image = new Image(new FileInputStream("src/sample/cardBackGround" + type + ".png"));
            ImageView backGroundCard = new ImageView(image);
            backGroundCard.relocate(x, y);
            group.getChildren().add(backGroundCard);


            Label minionLable = new Label(type.toUpperCase());
            if (type.matches("Minion")) {
                minionLable.relocate(x + 90, y + 135);
            } else if (type.matches("Spell")) {
                minionLable.relocate(x + 94, y + 135);
            } else if (type.matches("Hero")) {
                minionLable.relocate(x + 94, y + 135);
            } else {
                minionLable.relocate(x + 94, y + 135);
            }
            minionLable.setTextFill(Color.LIGHTBLUE);
            group.getChildren().add(minionLable);

            StackPane stackPane1 = new StackPane();
            Rectangle rectangle = new Rectangle(226, 0);
            stackPane1.relocate(x, y + 115);
            Label nameLable = new Label(name);
            nameLable.setTextFill(Color.WHITE);
            nameLable.setScaleY(1.2);
            nameLable.setScaleX(1.2);
            stackPane1.getChildren().addAll(rectangle, nameLable);
            group.getChildren().add(stackPane1);

            if (!(type.matches("Hero")) && !(type.matches("Item"))) {
                Image image1 = new Image(new FileInputStream("src/sample/mana.png"));
                ImageView manaView = new ImageView(image1);
                Label manaLable = new Label(Integer.toString(MP));
                manaLable.setScaleX(1.3);
                manaLable.setScaleY(1.3);
                manaLable.setTextFill(Color.DARKBLUE);
                manaLable.setStyle("-fx-font-weight: bold");
                StackPane stackPane = new StackPane();
                stackPane.getChildren().addAll(manaView, manaLable);
                stackPane.relocate(x - 26, y - 30);
                group.getChildren().add(stackPane);
            }

            if (!(type.matches("Spell")) && !(type.matches("Item"))) {

                StackPane stackPane2 = new StackPane();
                Rectangle rectangle1 = new Rectangle(25, 0);
                stackPane2.relocate(x + 40, y + 170);
                Label APLable = new Label(Integer.toString(AP));
                APLable.setTextFill(Color.YELLOW);
                APLable.setScaleX(1.2);
                APLable.setScaleY(1.2);
                stackPane2.getChildren().addAll(rectangle1, APLable);
                group.getChildren().add(stackPane2);

                StackPane stackPane3 = new StackPane();
                Rectangle rectangle2 = new Rectangle(25, 0);
                group.getChildren().addAll(stackPane3);
                stackPane3.relocate(x + 160, y + 170);
                Label label = new Label(Integer.toString(HP));
                label.setTextFill(Color.RED);
                label.setScaleX(1.2);
                label.setScaleY(1.2);
                stackPane3.getChildren().addAll(rectangle2, label);

                StackPane stackPane7 = new StackPane();
                Rectangle rectangle6 = new Rectangle(226, 0);
                Label typeAttackLable = new Label(typeAttack);
                typeAttackLable.setTextFill(Color.GRAY);
                stackPane7.getChildren().addAll(rectangle6, typeAttackLable);
                stackPane7.relocate(x, y + 250);
                group.getChildren().add(stackPane7);

            }

            StackPane stackPane4 = new StackPane();
            StackPane stackPane5 = new StackPane();
            Rectangle rectangle3 = new Rectangle(226, 0);
            Rectangle rectangle4 = new Rectangle(226, 0);
            Label line1 = new Label(getDescForCard(desc).get(0));
            Label line2 = new Label();
            line1.setTextFill(Color.LIGHTSKYBLUE);
            line2.setTextFill(Color.LIGHTSKYBLUE);
            if (getDescForCard(desc).size() == 2) {
                line2.setText(getDescForCard(desc).get(1));
                group.getChildren().add(stackPane5);
            }
            stackPane4.relocate(x, y + 212);
            stackPane5.relocate(x, y + 230);
            stackPane4.getChildren().addAll(rectangle3, line1);
            stackPane5.getChildren().addAll(rectangle4, line2);
            group.getChildren().add(stackPane4);

            if (!imageName.matches("")) {
                StackPane stackPane6 = new StackPane();
                Rectangle rectangle5 = new Rectangle(226, 0);
                Image image2 = new Image(new FileInputStream("src/image/" + type + "/" + imageFolder + imageName));
                ImageView imageView = new ImageView(image2);
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                stackPane6.getChildren().addAll(rectangle5, imageView);
                stackPane6.relocate(x, y + 15);
                imageView.setScaleX(1.2);
                imageView.setScaleY(1.2);
                group.getChildren().add(stackPane6);
            }

        } catch (Exception e) {

        }

        return group;
    }

    public static ArrayList<String> getDescForCard(String desc) {

        ArrayList<String> strings = new ArrayList<String>();
        if (desc.length() <= 25) {
            strings.add(desc);
        } else {
            String string = "";
            String string1 = "";
            for (int i = 0; i < 10; i++) {
                if (desc.charAt(24 - i) == ' ') {
                    string = desc.substring(0, 24 - i);
                    string1 = desc.substring(24 - i + 1, desc.length());
                    break;
                }
            }
            strings.add(string);
            strings.add(string1);
        }

        return strings;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();


        startGameEnviroment();


    }

    public static void main(String[] args) {
        Main.fisrt();
        launch(args);
        MainMenu.saveAccounts();
    }

}
