import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class MainGame {

	@FXML
	private Rectangle a;
	@FXML
	private Rectangle b;
	@FXML
	private Rectangle c;
	@FXML
	private Rectangle d;

	@FXML
	private Rectangle e;
	@FXML
	private Rectangle f;
	@FXML
	private Rectangle g;
	@FXML
	private Rectangle h;

	@FXML
	private Rectangle i;
	@FXML
	private Rectangle j;
	@FXML
	private Rectangle k;
	@FXML
	private Rectangle l;

	@FXML
	private Rectangle m;
	@FXML
	private Rectangle n;
	@FXML
	private Rectangle o;
	@FXML
	private Rectangle p;

	private Rectangle[][] rec;

	@FXML
	private Button start;

	@FXML
	private Label a1;
	@FXML
	private Label b1;
	@FXML
	private Label c1;
	@FXML
	private Label d1;

	@FXML
	private Label e1;
	@FXML
	private Label f1;
	@FXML
	private Label g1;
	@FXML
	private Label h1;

	@FXML
	private Label i1;
	@FXML
	private Label j1;
	@FXML
	private Label k1;
	@FXML
	private Label l1;

	@FXML
	private Label m1;
	@FXML
	private Label n1;
	@FXML
	private Label o1;
	@FXML
	private Label p1;

	private Label[][] lab;
	private int[] numbers = new int[16];
	private int[] textSize = { 30, 25, 24 };

	private int[] count = new int[18];

	@FXML
	private void mainGame() {
		invisibleLabels();
		rec = new Rectangle[][] { { a, b, c, d }, { e, f, g, h }, { i, j, k, l }, { m, n, o, p } };
		lab = new Label[][] { { a1, b1, c1, d1 }, { e1, f1, g1, h1 }, { i1, j1, k1, l1 }, { m1, n1, o1, p1 } };
		setCount();
		resetNumbers();
		setNum();
		start.setVisible(false);
		start.setDisable(true);
		move();
	}

	private void move() {
		EventHandler<KeyEvent> eventHandler = e -> {
			if (e.getCode() == KeyCode.DOWN) {
				if (checkD()) {
					//System.out.println("down");
					moveDown();
					numberToLabel();
					setNum();
				}
			} else if (e.getCode() == KeyCode.UP) {
				if (checkU()) {
					//System.out.println("up");
					moveUp();
					numberToLabel();
					setNum();
				}
			} else if (e.getCode() == KeyCode.LEFT) {
				if (checkL()) {
					//System.out.println("left");
					moveLeft();
					numberToLabel();
					setNum();
				}
			} else if (e.getCode() == KeyCode.RIGHT) {
				if (checkR()) {
					//System.out.println("right");
					moveRight();
					numberToLabel();
					setNum();
				}
			}
		};
		MainStage.scene.setOnKeyPressed(eventHandler);
	}

	private int pop() {
		double p = Math.random();
		if(p > 0.995)
			return 8;
		else if (p > 0.75)
			return 4;
		return 2;
	}

	private int getNumberPosition() {
		int a = -1;
		do {
			a = (int) (Math.random() * 16);
		} while (numbers[a] != -1);
		return a;
	}

	private void resetNumbers() {
		for (int i = 0; i < 16; i++) {
			numbers[i] = -1;
		}
	}

	private void setCount() {
		for (int i = 0; i < count.length; i++) {
			count[i] = (int) Math.pow(2, i);
		}
	}

	private void fill(Rectangle rec, int number) {
		if (number == count[1] || number == count[2])
			rec.setFill(Color.ANTIQUEWHITE);
		if (number == count[3])
			rec.setFill(Color.LAVENDER);
		if (number == count[4])
			rec.setFill(Color.LEMONCHIFFON);
		if (number == count[5])
			rec.setFill(Color.SKYBLUE);
		if (number == count[6])
			rec.setFill(Color.LIGHTGREEN);
		if (number == count[7])
			rec.setFill(Color.LIGHTCYAN);
		if (number == count[8])
			rec.setFill(Color.LIGHTYELLOW);
		if (number == count[9])
			rec.setFill(Color.LIGHTPINK);
		if (number == count[10])
			rec.setFill(Color.PALETURQUOISE);
		if (number == count[11] || number == count[15])
			rec.setFill(Color.GOLD);
		if (number == count[12] || number == count[16])
			rec.setFill(Color.AQUAMARINE);
		if (number == count[13] || number == count[17])
			rec.setFill(Color.RED);
		if (number == count[14])
			rec.setFill(Color.VIOLET);

	}

	private void setNum() {
		if (!gameover()) {
			int popOut = pop();
			int pos = getNumberPosition();
			numbers[pos] = popOut;
			int x = pos / 4;
			int y = pos % 4;
			fill(rec[x][y], popOut);
			//rec[x][y].setFill(Color.ANTIQUEWHITE);
			lab[x][y].setText("" + numbers[pos]);
			lab[x][y].setVisible(true);
			lab[x][y].setFont(Font.font("Helvtica", FontWeight.BOLD, FontPosture.REGULAR,
					getFontSize(popOut)));
		} else {
			System.out.println("Game Over");
		}
	}

	private void moveRight() {
		for (int i = 0; i < 4; i++) {
			fillEmptR(i);
			if (numbers[i * 4 + 3] == numbers[i * 4 + 2] && numbers[i * 4 + 3] != -1) {
				numbers[i * 4 + 3] *= 2;
				numbers[i * 4 + 2] = -1;
				fillEmptR(i);
			}
			if (numbers[i * 4 + 2] == numbers[i * 4 + 1] && numbers[i * 4 + 2] != -1) {
				numbers[i * 4 + 2] *= 2;
				numbers[i * 4 + 1] = -1;
				fillEmptR(i);
			}
			if (numbers[i * 4 + 1] == numbers[i * 4 + 0] && numbers[i * 4 + 1] != -1) {
				numbers[i * 4 + 1] *= 2;
				numbers[i * 4 + 0] = -1;
				fillEmptR(i);
			}
		}
	}

	private void moveDown() {
		for (int i = 0; i < 4; i++) {
			fillEmptD(i);
			if (numbers[i + 4 * 3] == numbers[i + 4 * 2] && numbers[i + 4 * 3] != -1) {
				numbers[i + 4 * 3] *= 2;
				numbers[i + 4 * 2] = -1;
				fillEmptD(i);
			}
			if (numbers[i + 4 * 2] == numbers[i + 4 * 1] && numbers[i + 4 * 2] != -1) {
				numbers[i + 4 * 2] *= 2;
				numbers[i + 4 * 1] = -1;
				fillEmptD(i);
			}
			if (numbers[i + 4 * 1] == numbers[i + 4 * 0] && numbers[i + 4 * 1] != -1) {
				numbers[i + 4 * 1] *= 2;
				numbers[i + 4 * 0] = -1;
				fillEmptD(i);
			}
		}
	}

	private void moveLeft() {
		for (int i = 0; i < 4; i++) {
			fillEmptL(i);
			if (numbers[i * 4 + 0] == numbers[i * 4 + 1] && numbers[i * 4 + 0] != -1) {
				numbers[i * 4 + 0] *= 2;
				numbers[i * 4 + 1] = -1;
				fillEmptL(i);
			}
			if (numbers[i * 4 + 1] == numbers[i * 4 + 2] && numbers[i * 4 + 1] != -1) {
				numbers[i * 4 + 1] *= 2;
				numbers[i * 4 + 2] = -1;
				fillEmptL(i);
			}
			if (numbers[i * 4 + 2] == numbers[i * 4 + 3] && numbers[i * 4 + 2] != -1) {
				numbers[i * 4 + 2] *= 2;
				numbers[i * 4 + 3] = -1;
				fillEmptL(i);
			}
		}
	}

	private void moveUp() {
		for (int i = 0; i < 4; i++) {
			fillEmptU(i);
			if (numbers[i + 4 * 0] == numbers[i + 4 * 1] && numbers[i + 4 * 0] != -1) {
				numbers[i + 4 * 0] *= 2;
				numbers[i + 4 * 1] = -1;
				fillEmptU(i);
			}
			if (numbers[i + 4 * 1] == numbers[i + 4 * 2] && numbers[i + 4 * 1] != -1) {
				numbers[i + 4 * 1] *= 2;
				numbers[i + 4 * 2] = -1;
				fillEmptU(i);
			}
			if (numbers[i + 4 * 2] == numbers[i + 4 * 3] && numbers[i + 4 * 2] != -1) {
				numbers[i + 4 * 2] *= 2;
				numbers[i + 4 * 3] = -1;
				fillEmptU(i);
			}
		}
	}

	private void numberToLabel() {
		for (int i = 0; i < 16; i++) {
			int x = i / 4;
			int y = i % 4;
			if (numbers[i] != -1) {
				fill(rec[x][y], numbers[i]);
				//rec[x][y].setFill(Color.ANTIQUEWHITE);
				lab[x][y].setText("" + numbers[i]);
				lab[x][y].setVisible(true);
				lab[x][y].setFont(Font.font("Helvtica", FontWeight.BOLD, FontPosture.REGULAR,
						getFontSize(numbers[i])));
			} else {
				lab[x][y].setVisible(false);
				rec[x][y].setFill(Color.rgb(234, 234, 234));
			}
		}
	}

	private void fillEmptR(int i) {
		// Fill the empty spaces
		for (int j = 2; j >= 0; j--) {
			for (int n = 3; n > j; n--) {
				if (numbers[i * 4 + j] != -1 && numbers[i * 4 + n] == -1) {
					numbers[i * 4 + n] = numbers[i * 4 + j];
					numbers[i * 4 + j] = -1;
					break;
				}
			}
		}
	}

	private void fillEmptD(int i) {
		// Fill the empty spaces
		for (int j = 2; j >= 0; j--) {
			for (int n = 3; n > j; n--) {
				if (numbers[i + 4 * j] != -1 && numbers[i + 4 * n] == -1) {
					numbers[i + 4 * n] = numbers[i + 4 * j];
					numbers[i + 4 * j] = -1;
					break;
				}
			}
		}
	}

	private void fillEmptL(int i) {
		// Fill the empty spaces
		for (int j = 1; j <= 3; j++) {
			for (int n = 0; n < j; n++) {
				if (numbers[i * 4 + j] != -1 && numbers[i * 4 + n] == -1) {
					numbers[i * 4 + n] = numbers[i * 4 + j];
					numbers[i * 4 + j] = -1;
					break;
				}
			}
		}
	}

	private void fillEmptU(int i) {
		// Fill the empty spaces
		for (int j = 1; j <= 3; j++) {
			for (int n = 0; n < j; n++) {
				if (numbers[i + 4 * j] != -1 && numbers[i + 4 * n] == -1) {
					numbers[i + 4 * n] = numbers[i + 4 * j];
					numbers[i + 4 * j] = -1;
					break;
				}
			}
		}
	}

	private boolean gameover() {
		boolean over = true;
		for (int i = 0; i < 16; i++) {
			if(numbers[i] == -1){
				over = false;
				break;
			}
			// u d l r not identical number then game over!
			try {
				if (numbers[i] == numbers[i + 1]){
					over = false;
					break;
				}
			} catch (Exception e) {

			}
			try {
				if (numbers[i] == numbers[i - 1]){
					over = false;
					break;
				}
			} catch (Exception e) {

			}
			try {
				if (numbers[i] == numbers[i + 4]){
					over = false;
					break;
				}
			} catch (Exception e) {

			}
			try {
				if (numbers[i] == numbers[i - 4]){
					over = false;
					break;
				}
			} catch (Exception e) {

			}
		}
		return over;
	}

	private int getFontSize(int num) {
		if (num < 10000)
			return textSize[0];
		else if (num < 100000)
			return textSize[1];
		return textSize[2];
	}

	private void invisibleLabels() {
		a1.setVisible(false);
		b1.setVisible(false);
		c1.setVisible(false);
		d1.setVisible(false);

		e1.setVisible(false);
		f1.setVisible(false);
		g1.setVisible(false);
		h1.setVisible(false);

		i1.setVisible(false);
		j1.setVisible(false);
		k1.setVisible(false);
		l1.setVisible(false);

		m1.setVisible(false);
		n1.setVisible(false);
		o1.setVisible(false);
		p1.setVisible(false);
	}

	private boolean checkR() {
		boolean yes = false;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				if (numbers[i * 4 + j] != -1 && numbers[i * 4 + j + 1] == -1)
					yes = true;
				if (numbers[i * 4 + j] != -1 && numbers[i * 4 + j + 1] == numbers[i * 4 + j])
					yes = true;
			}
		}
		return yes;
	}

	private boolean checkL() {
		boolean yes = false;
		for (int i = 0; i < 4; i++) {
			for (int j = 3; j > 0; j--) {
				if (numbers[i * 4 + j] != -1 && numbers[i * 4 + j - 1] == -1)
					yes = true;
				if (numbers[i * 4 + j] != -1 && numbers[i * 4 + j - 1] == numbers[i * 4 + j])
					yes = true;
			}
		}
		return yes;
	}

	private boolean checkD() {
		boolean yes = false;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				if (numbers[i + 4 * j] != -1 && numbers[i + 4 * (j + 1)] == -1)
					yes = true;
				if (numbers[i + 4 * j] != -1 && numbers[i + 4 * (j + 1)] == numbers[i + 4 * j])
					yes = true;
			}
		}
		return yes;
	}

	private boolean checkU() {
		boolean yes = false;
		for (int i = 0; i < 4; i++) {
			for (int j = 3; j > 0; j--) {
				if (numbers[i + 4 * j] != -1 && numbers[i + 4 * (j - 1)] == -1)
					yes = true;
				if (numbers[i + 4 * j] != -1 && numbers[i + 4 * (j - 1)] == numbers[i + 4 * j])
					yes = true;
			}
		}
		return yes;
	}

}
