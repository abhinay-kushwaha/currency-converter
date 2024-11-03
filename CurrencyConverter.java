import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CurrencyConverter extends JFrame implements ActionListener {
    private JComboBox<String> fromCurrency, toCurrency;
    private JTextField amountField, resultField;
    private JButton convertButton;
    private HashMap<String, Double> exchangeRates;

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Initialize exchange rates (these are example rates)
        exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("GBP", 0.75);
        exchangeRates.put("INR", 74.15);
        exchangeRates.put("AUD", 1.35);
        exchangeRates.put("CAD", 1.25);
        exchangeRates.put("JPY", 110.25);
        exchangeRates.put("CNY", 6.45);

        // Labels and input fields
        add(new JLabel("Amount:"));
        amountField = new JTextField();
        add(amountField);

        add(new JLabel("From Currency:"));
        fromCurrency = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        add(fromCurrency);

        add(new JLabel("To Currency:"));
        toCurrency = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        add(toCurrency);

        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);
        add(convertButton);

        add(new JLabel("Converted Amount:"));
        resultField = new JTextField();
        resultField.setEditable(false);
        add(resultField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();

            if (from != null && to != null) {
                double fromRate = exchangeRates.get(from);
                double toRate = exchangeRates.get(to);

                // Convert the amount
                double convertedAmount = (amount / fromRate) * toRate;
                resultField.setText(String.format("%.2f", convertedAmount));
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurrencyConverter converter = new CurrencyConverter();
            converter.setVisible(true);
        });
    }
}
