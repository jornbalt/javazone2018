package net.baltzersen.voting.cjt;

public class Main {

    public static void main(String[] args)
    {
        CJT cjt = new CJT();
        long argLen = args.length;
        if (argLen < 2)
        {
            throw new IllegalArgumentException();
        }
        long groupSize = Long.parseLong(args[0].toString());
        double individualCompetence = Double.parseDouble(args[1].toString());
        cjt.calculateGroupCompetence(groupSize, individualCompetence);
    }
}
