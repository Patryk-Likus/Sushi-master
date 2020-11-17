package creatorSushi;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


    enum EComponent{
        Avocado, ButterFish, Caviar, Kanpyo, Kappa, Nori, Oshinko, Rice, Salmon, Shrimp, Taco, Tuna;
    }

    enum ESushi{

        KappaMaki("Kappa Maki", EComponent.Kappa, EComponent.Rice, EComponent.Nori),
        VegeFutomaki("Vege Futomaki", EComponent.Kappa, EComponent.Rice, EComponent.Avocado, EComponent.Oshinko, EComponent.Nori, EComponent.Kanpyo),
        GunkanCaviar("Gunkan Caviar", EComponent.Caviar, EComponent.Rice, EComponent.Nori);

        private String title;
        private List<EComponent> componentList;

        private ESushi(String title, EComponent ... components){
            this.title = title;
            this.componentList = Arrays.asList(components);
        }

        public String getTitle() {
            return title;
        }
        public List<EComponent> getComponentList(){
            return componentList;
        }
    }

    public class Main extends JPanel {
        private int score = 0;
        private int life = 5;
        private List<EComponent> componentList;
        private JLabel text;
        private ESushi random;

        public Main() throws IOException {
            try {
                this.componentList = new ArrayList<EComponent>();
                setBackground(Color.white);
                setLayout(null);
                createPanelButton();
                createPanelText();
                createPanelChecl();
                createButtonX();
            } catch (Exception e) {
                e.getMessage();
            }
        }


    public static void main(String[] args) {


    }
}
