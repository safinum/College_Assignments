package bbb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private RadioButton circleRadio = new RadioButton();

    @FXML
    private RadioButton triangleRadio = new RadioButton();

    @FXML
    private RadioButton rectangleRadio = new RadioButton();

    @FXML
    private AnchorPane anchorPane = new AnchorPane();

    @FXML
    private Pane pane = new Pane(); 

    @FXML
    private Button edit = new Button();

    @FXML
    private Button clear = new Button();

    @FXML
    private MenuBar menuBar = new MenuBar();

    @FXML
    private MenuItem instrukcja = new MenuItem();

    @FXML
    private MenuItem informacje = new MenuItem();

    @FXML
    private TextArea textArea = new TextArea();

    @FXML
    private Pane paneTwo = new Pane();

    //listy, do ktorych beda dodawane figury w celu edycji
    private static final List<Circle> circles = new ArrayList<>();
    private static final List<Shape> shapes = new ArrayList<>();
    private static final List<Polygon> triangles = new ArrayList<>();
    private static final List<Rectangle> rectangles = new ArrayList<>();

    /* Rysuje koło. */
    public void rysujKolo () {

        //zapobiegnij rysowaniu innych figur i edycji
        pane.setOnMouseClicked(null);
        for (Shape f : shapes ) {f.setOnMouseClicked(null);}

        final Delta delta = new Delta(pane);

        //miejsce wcisniecia myszki to srodek kola
        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                delta.x = event.getX();
                delta.y = event.getY();
            }
        });

        //miejsce zwolnienia myszki to punkt obwodu
        pane.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double x = event.getX();
                double y = event.getY();

                //obliczanie dlugosc promienia
                double radius = Math.abs(Math.sqrt(Math.pow((x - delta.x), 2) + Math.pow((y - delta.y), 2)));
    
                //tworzenie kola
                Circle circle = new Circle();
                circle.setCenterX(delta.x);
                circle.setCenterY(delta.y);
                circle.setRadius(radius);
                circle.setFill(Color.LIGHTBLUE);
                circle.setStroke(Color.BLACK);
                circle.setStrokeWidth(1);
                delta.pane.getChildren().add(circle);
                circles.add(circle);
            }
        });
    }

    /** Zaznacza 3 punkty. Jeśli się to uda, tworzy trójkąt. */
    public void zaznaczTrojkat () { 

        //zapobiegnij rysowaniu innych figur i edycji
        pane.setOnMousePressed(null);
        pane.setOnMouseReleased(null);
        for (Shape f : shapes ) {f.setOnMouseClicked(null);}

        final Delta delta = new Delta(pane);

        //licznik klikniec
        delta.clickCounter = 0;

        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //kolejne klikniecia to punkty trojkata
                if (delta.clickCounter == 0) {
                    delta.x = event.getX();
                    delta.y = event.getY();
                    delta.clickCounter = 1;
                }
                else if (delta.clickCounter == 1) {
                    delta.x2 = event.getX();
                    delta.y2 = event.getY();
                    delta.clickCounter = 2;
                }
                else {
                    double thirdX = event.getX();
                    double thirdY = event.getY();
    
                    //utworz trojkat i ustaw licznik na zero
                    Polygon triangle = new Polygon();
                    triangle.getPoints().addAll(
                        delta.x, delta.y,
                        delta.x2, delta.y2,
                        thirdX, thirdY
                    );
                    triangle.setFill(Color.PALEGREEN);
                    triangle.setStroke(Color.BLACK);
                    triangle.setStrokeWidth(1);
                    pane.getChildren().add(triangle);
                    triangles.add(triangle);
                    delta.clickCounter = 0;
                }
            }
        });
    }
    
    /** Rysuje prostokąt. */
    public void rysujProstokat () { 

        //zapobiegnij rysowaniu innych figur i edycji
        pane.setOnMouseClicked(null);
        for (Shape f : shapes ) {f.setOnMouseClicked(null);}

            final Delta delta = new Delta(pane);

            //miejsce wcisniecia myszki to pierwszy wierzcholek prostokata
            pane.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
                    delta.x = event.getX();
                    delta.y = event.getY();
				}
            });

            //miejsce zwolnienia myszki to koniec przekatnej prostokata
            pane.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    double deltaX = event.getX() - delta.x;
                    double deltaY = event.getY() - delta.y;  
                
                    Rectangle rectangle = new Rectangle();
                    
                    //sprawdz wspolrzedne konca przekatnej, zeby narysowac prostokat w odpowiednim kierunku
                    if( deltaX < 0 ) {
                        rectangle.setX(event.getX() );
                        rectangle.setWidth(-deltaX);
                        }
                    else {
                        rectangle.setX(delta.x);
                        rectangle.setWidth(event.getX() - delta.x);
                    }
        
                    if( deltaY < 0 ) {
                        rectangle.setY( event.getY() );
                        rectangle.setHeight( -deltaY );
                    }
                    else {
                        rectangle.setY( delta.y );
                        rectangle.setHeight( event.getY() - delta.y );
                    }

                    rectangle.setFill(Color.GRAY);
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setStrokeWidth(1);
                    pane.getChildren().add(rectangle);
                    rectangles.add(rectangle);
                }
            });
    }

    /** Pozwala na edycję figury - oznaczanie jako aktywnej, przesuwanie, zmianę rozmiaru
     * oraz zmianę koloru i obracanie za pomocą menu kontekstowego.
     */
    public void edytuj () {

        //odznacz radio buttons
        odznaczFigury();

        //dodaj wszystkie figury do listy SHAPES
        for (int i = 0; i < circles.size(); i++) {
            shapes.add(circles.get(i));
        }

        for (int i = 0; i < triangles.size(); i++) {
            shapes.add(triangles.get(i));
        }

        for (int i = 0; i < rectangles.size(); i++) {
            shapes.add(rectangles.get(i));
        }

        //zapobiegnij rysowaniu figur w trybie edycji
        resetHandlers();

        final Delta delta = new Delta(pane);

        for (Shape f : shapes ) {
                //jesli figura zostanie kliknieta, ustaw jej kolor figury aktywnej
                //i dodaj mozliwosc zmiany rozmiaru, przesuwania oraz korzystania z 
                //menu kontekstowego
                f.setOnMouseClicked(new EventHandler<MouseEvent>() { 
					@Override
					public void handle(MouseEvent event) {

                            f.setFill(delta.kolor);
                            if (f.getFill() == delta.kolor) {
                                addMouseScrolling(f);
                                moveFigure(f);
                                setContextMenu(f);
                            }                                        
					}
                });   
        }
    }

    /** Przesuwa Node po panelu. */
    private void moveFigure (Node node) {
        final Delta delta = new Delta(pane);
        node.setOnMousePressed(new EventHandler<MouseEvent>() { 
            @Override public void handle(MouseEvent ev) {
              delta.x = node.getLayoutX() - ev.getSceneX();
              delta.y = node.getLayoutY() - ev.getSceneY();
            }
          });
          node.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent ev) {
              node.setLayoutX(ev.getSceneX() + delta.x);
              node.setLayoutY(ev.getSceneY() + delta.y);
            }
          });
    }

    /** Dodaje do Node menu kontekstowe ze zmianą koloru i obracaniem. */
    private void setContextMenu (Node node) {
        final Delta delta = new Delta(pane);

        //pierwsza opcja menu to wybor koloru ColorPickerem
        final ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue((Color) delta.kolor);

        ContextMenu contextMenu = new ContextMenu();
        final MenuItem menuItem1 = new MenuItem("Wybierz kolor", colorPicker);
        menuItem1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event)
            {
                ((Shape) node).setFill(colorPicker.getValue());
            }
        });

        //druga opcja to obracanie figury
        final MenuItem menuItem2 = new MenuItem("Obroc");
        menuItem2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				node.getTransforms().add(new Rotate(90.0, node.getBoundsInLocal().getCenterX(),node.getBoundsInLocal().getCenterY()));
				
			}
           });

           contextMenu.getItems().add(menuItem1); 
           contextMenu.getItems().add(menuItem2);
          
           //pokaz menu w odpowiednim miejscu
           node.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(node, event.getScreenX(), event.getScreenY());
            }
        });
    } 

    /** Dodaje zmianę rozmiaru Node za pomocą scrolla. */
    private void addMouseScrolling (Node node) {
        node.setOnScroll((ScrollEvent event) -> {
            double zoom = 1.05;
            double deltaY = event.getDeltaY();
            if (deltaY < 0){
                zoom = 1.75 - zoom;
            }
            node.setScaleX(node.getScaleX() * zoom);
            node.setScaleY(node.getScaleY() * zoom);
        });
    }

    /** Usuwa figury z panelu. */
    public void wyczysc () {
        pane.getChildren().clear();
        shapes.clear();
        odznaczFigury();
        resetHandlers();
    }

    /** Odznacza RadioButtons z menu po lewej stronie. */
    private void odznaczFigury() {
        circleRadio.setSelected(false);
        triangleRadio.setSelected(false);
        rectangleRadio.setSelected(false);
    }

    /** Usuwa handlery. */
    private void resetHandlers() {
        pane.setOnMouseClicked(null);
        pane.setOnMousePressed(null);
        pane.setOnMouseReleased(null);
    }

    /** Pokazuje okno z instrukcjami. */
    public void showInstruction (ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene2.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage(); 
        stage.setScene(new Scene(root1));
        stage.setTitle("Instrukcja użytkowania programu");
        stage.setResizable(false);
        stage.show(); 
    } 

    /** Pokazuje okno dialogowe z informacjami o programie. */
    public void showInfo() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Informacje o programie");
        alert.setHeaderText("Program do rysowania figur geometrycznych.\nAutor: Kamila Górniak\n");
        alert.setContentText("Zamknij okno dialogowe");

        if (alert.showAndWait().get() == ButtonType.OK) {
            alert.close();
        }
    }

    private class Delta { double x, y, x2, y2, clickCounter; Paint kolor = Color.LIGHTGOLDENRODYELLOW; Pane pane; Delta(Pane pane){this.pane = pane;} }
}