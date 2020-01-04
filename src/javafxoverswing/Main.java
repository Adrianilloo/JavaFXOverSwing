package javafxoverswing;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("JavaFXOverSwing.fxml"));
			Scene scene1 = new Scene(root);
			Label label = new Label("JavaFX over Swing");
			label.setFont(new Font(18));
			Scene scene2 = new Scene(label);
			label.setAlignment(Pos.CENTER);
			JFXPanel jFXPanel1 = new JFXPanel(), jFXPanel2 = new JFXPanel();

			// We assign scenes early because only doing it allows to query the real sizes
			jFXPanel1.setScene(scene1);
			jFXPanel2.setScene(scene2);

			SwingUtilities.invokeAndWait(() ->
			{
				// First window - JFrame
				JFrame jFrame = new JFrame("Swing and JavaFX");
				jFrame.setTitle("JavaFX over Swing - JFrame");
				jFrame.add(jFXPanel1);
				jFrame.setVisible(true); // Doing this allows to query real bounds created by decorators

				// We set the window position relative to screen.
				// If applied on JFXPanel, the content is moved within the self window, instead.
				jFrame.setBounds(200, 100, jFrame.getWidth() + (int) scene1.getWidth(),
						jFrame.getHeight() + (int) scene1.getHeight());

				// Second window - JDialog
				JDialog jDialog = new JDialog();
				jDialog.setTitle("JavaFX over Swing - JDialog");
				jDialog.add(jFXPanel2);
				jDialog.setVisible(true);
				jDialog.setBounds(800, 200, jDialog.getWidth() + (int) scene1.getWidth(),
						jDialog.getHeight() + (int) scene1.getHeight());

				// Third window - Raw Swing Dialog - No Scene attaching
				JOptionPane.showConfirmDialog(null, "Swing and JavaFX");
			});
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
