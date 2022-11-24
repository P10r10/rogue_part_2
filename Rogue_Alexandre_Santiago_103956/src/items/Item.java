//package items;
//
//import pt.iscte.poo.gui.ImageTile;
//import pt.iscte.poo.utils.Point2D;
//
//public class Item implements ImageTile {
//
//	private String currentImage = "Black";
//	private Point2D position;
//
//	public Item(Point2D position) {
//		this.position = position;
//	}
//
//	public void setCurrentImage(String currentImage) {
//		this.currentImage = currentImage;
//	}
//
//	@Override
//	public String getName() {
//		return currentImage;
//	}
//
//	@Override
//	public Point2D getPosition() {
//		return position;
//	}
//
//	@Override
//	public int getLayer() {
//		return 0;
//	}
//}