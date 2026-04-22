package lichtman.fruity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FruitFrame extends JFrame {
    public FruitFrame() {
        setSize(800, 800);
        setTitle("Fruity Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());
        FruityService service = new FruityServiceFactory().create();

        JTextField searchField = new JTextField("strawberry");
        JButton searchButton = new JButton("Search");
        JLabel picLabel = new JLabel();

        final JLabel familyLabel = new JLabel("Family: ");
        final JLabel orderLabel = new JLabel("Order: ");
        final JLabel genusLabel = new JLabel("Genus: ");
        final JLabel caloriesLabel = new JLabel("Calories: ");
        final JLabel fatLabel = new JLabel("Fat: ");
        final JLabel sugarLabel = new JLabel("Sugar: ");
        final JLabel carbsLabel = new JLabel("Carbs: ");
        final JLabel proteinLabel = new JLabel("Proteins: ");

        JLabel familyInfo = new JLabel("");
        JLabel orderInfo = new JLabel("");
        JLabel genusInfo = new JLabel("");
        JLabel caloriesInfo = new JLabel("");
        JLabel fatInfo = new JLabel("");
        JLabel sugarInfo = new JLabel("");
        JLabel carbsInfo = new JLabel("");
        JLabel proteinInfo = new JLabel("");

        FruitController fController = new FruitController(
                service, picLabel,
                familyInfo, orderInfo, genusInfo,
                caloriesInfo, fatInfo, sugarInfo,
                carbsInfo, proteinInfo
        );

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fController.fruitInfo(searchField.getText());
            }
        });

        GridBagConstraints constraints;

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 3;
        add(searchField, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 0;
        add(searchButton, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridheight = 8;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 2;
        add(picLabel, constraints);

        // fruit info column pairs

        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 1;
        add(familyLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 1;
        add(familyInfo, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 2;
        add(orderLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 2;
        add(orderInfo, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 3;
        add(genusLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 3;
        add(genusInfo, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 4;
        add(caloriesLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 4;
        add(caloriesInfo, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 5;
        add(fatLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 5;
        add(fatInfo, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 6;
        add(sugarLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 6;
        add(sugarInfo, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 7;
        add(carbsLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 7;
        add(carbsInfo, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 8;
        add(proteinLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 8;
        add(proteinInfo, constraints);
    }
    public static void main(String[] args) {

        FruitFrame frame = new FruitFrame();
        frame.setVisible(true);
    }

}
