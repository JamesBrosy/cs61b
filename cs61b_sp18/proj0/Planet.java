public class Planet {
    private static final double GRAV_CONST = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xxPos, double yyPos, double xxVel,
                  double yyVel, double mass , String img) {
        this.xxPos       = xxPos;
        this.yyPos       = yyPos;
        this.xxVel       = xxVel;
        this.yyVel       = yyVel;
        this.mass        = mass;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos       = p.xxPos;
        this.yyPos       = p.yyPos;
        this.xxVel       = p.xxVel;
        this.yyVel       = p.yyVel;
        this.mass        = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double distance = calcDistance(p);
        return GRAV_CONST * this.mass * p.mass / (distance * distance);
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - this.xxPos;
        return calcForceExertedBy(p) * dx / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - this.yyPos;
        return calcForceExertedBy(p) * dy / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netForceExertedByX = 0;
        for (Planet item : allPlanets) {
            if (this.equals(item)) {
                continue;
            }
            netForceExertedByX += this.calcForceExertedByX(item);
        }
        return netForceExertedByX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netForceExertedByY = 0;
        for (Planet item : allPlanets) {
            if (this.equals(item)) {
                continue;
            }
            netForceExertedByY += this.calcForceExertedByY(item);
        }
        return netForceExertedByY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        xxVel += aX * dt;
        yyVel += aY * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }

}
