package creatorSushi;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
                createPanelCheck();
                createButtonX();
            } catch (Exception e) {
                e.getMessage();
            }
        }



        private void createButtonX() throws IOException {
            JLabel buttonX = new JLabel();
            buttonX.setIcon(new ImageIcon(ImageIO.read(new File("src/mmainImages/x.png"))));
            buttonX.setBounds(580, 100, 100, 50);

            buttonX.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    componentList.clear();
                    repaint();
                }
            });
            add(buttonX);
        }

        private void createPanelCheck() throws IOException {
            JLabel button = new JLabel();
            button.setIcon(new ImageIcon(ImageIO.read(new File("src/mainImages/check.png"))));
            button.setBounds(530, 50, 200, 50);


            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    Collections.sort(componentList);
                    Collections.sort(random.getComponentList());

                    if(componentList.equals(random.getComponentList())){
                        score++;
                        componentList.clear();
                        draw();
                    } else{
                        life--;
                        componentList.clear();
                    }
                    repaint();
                    isEndGame();
                }
                private void isEndGame(){
                    if(life < 1){
                        JOptionPane.showMessageDialog(null, "Twój wynik: " + score);
                        life = 5;
                        draw();
                        score = 0;
                        repaint();
                    }
                }
            });
            add(button);
        }

        private void createPanelText(){
            text = new JLabel();
            text.setBounds(100, 35, 400, 80);
            Font font = new Font("Arial", Font.BOLD, 25);

            draw();

            text.setFont(font);
            add(text);
        }
        public void draw(){
            int index = (int) (Math.random() * ESushi.values().length);
            random = ESushi.values()[index];
            text.setText(random.getTitle());
        }
        public void createPanelButton() throws IOException {
            JPanel buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new GridLayout(3,4));

            File[] graphics = new File("src/components/").listFiles();

            for(File f: graphics){
                JLabel button = new JLabel();

                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        componentList.add(EComponent.valueOf(f.getName().substring(0, f.getName().length() - 4)));
                        repaint();
                    }
                });

                button.setIcon(new ImageIcon((ImageIO.read(f))));
                buttonsPanel.add(button);
            }
            buttonsPanel.setBounds(0, 170, 800, 600);
            add(buttonsPanel);

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            File heart = new File("src/mainImages/serce.png");

            for(int i = 0; i < life; i++){
                try{
                    g.drawImage(ImageIO.read(heart), (i * 30) + (i + 1), 10, 20, 20, null);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.drawString("Punkty: " + score, 570, 30);
            }

            for(int i = 0; i < componentList.size(); i++){
                try{
                    g.drawImage(ImageIO.read(new File("src/components/" + componentList.get(i) + ".png")),
                            50 * i, 120, 50, 50, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(770, 770);
        }


        public static void main(String[] args) throws IOException {

            JFrame window = new JFrame("Kreator Sushi");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setLocation(575,155);
            window.setVisible(true);

            window.add(new Main());

            window.pack();
        }
    }


