public class Matrix2X2{
    private final double xx,xy,yx,yy;
    
    private Matrix2X2(double xx, double xy, double yx, double yy){
	this.xx=xx; this.xy=xy;
	this.yx=yx; this.yy=yy;
    }
    public Vector2D apply(Vector2D v){
	return new Vector2D(xx*v.x() + xy*v.y(), yx*v.x() + yy*v.y());
    }
    public Matrix2X2 multiply(Matrix2X2 m){
	return new Matrix2X2((xx*m.xx + xy*m.yx),(xx*m.xy + xy*m.yy),
			     (yx*m.xx + yy*m.yx),(yx*m.xy + yy*m.yy));
    }
    public static Matrix2X2 rotate(double t){
	return new Matrix2X2(Math.cos(t), -Math.sin(t),
			     Math.sin(t),  Math.cos(t));
    }
    public static Matrix2X2 scale(double s){
	return new Matrix2X2(s,0.0,0.0,s);
    }
}