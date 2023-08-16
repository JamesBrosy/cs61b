public class NBody {
    public static double readRadius(String filePath) {
        In in = new In(filePath);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String filePath) {
        In in = new In(filePath);
        int total = in.readInt();
        in.readDouble();

        Planet[] planets = new Planet[total];
        double xxPos;
        double yyPos;
        double xxVel;
        double yyVel;
        double mass;
        String img;

        for (int i = 0; i < total; i++) {
            xxPos = in.readDouble();
            yyPos = in.readDouble();
            xxVel = in.readDouble();
            yyVel = in.readDouble();
            mass  = in.readDouble();
            img   = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
        }
        return planets;
    }

    public static void main(String[] args) {
        /* initialize command parameters */
        double T  = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);

        /* set scales and clear canvas */
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        /* Draw universe canvas */
        StdDraw.picture(0, 0, "images/starfield.jpg");

        /* display all planets on the universe canvas */
        for (Planet planet : planets) {
            planet.draw();
        }

        /* Enable double buffering */
        StdDraw.enableDoubleBuffering();

        /* display all planets on the universe canvas */
        double time = 0;
        while (time < T) {

            /* cache fX and fY of all planets respectively in the
             *  array {@code xForces} and array {@code yForces}
             */
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            /* Display all planets on the universe canvas */
            for (Planet planet : planets) {
                planet.draw();
            }

            /* Show the offscreen buffer */
            StdDraw.show();

            /* Pause the animation for 10 milliseconds */
            StdDraw.pause(10);

            /* Update time */
            time += dt;
        }

        /* Print the ultimate universe */
        System.out.println(planets.length);
        System.out.printf("%.2e\n", radius);
        for (Planet planet : planets) {
            System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planet.xxPos, planet.yyPos, planet.xxVel,
                    planet.yyVel, planet.mass, planet.imgFileName);
        }
    }
}
