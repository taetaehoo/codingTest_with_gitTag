import java.util.Scanner;

class Gear {
    private int[] gear;
    private int northPointer;

    public Gear(int[] gear) {
        this.gear = gear;
        this.northPointer = 0;
    }

    public int getNorthVal() {
        return gear[northPointer];
    }

    public int getEastVal() {
        return gear[(northPointer + 2) % 8];
    }

    public int getWestVal() {
        return gear[(northPointer + 6) % 8];
    }

    public void rotate(int dir) {
        if (dir == 1) {
            northPointer = (northPointer + 7) % 8;
        }
        else {
            northPointer = (northPointer + 1) % 8;
        }
    }
}

class GearRotator {
    Gear[] gears;

    public GearRotator(Gear[] gears) {
        this.gears = gears;
    }

    public void rotate(int gearNum, int dir) {
        boolean firstGear = gears[0].getEastVal() != gears[1].getWestVal();
        boolean secondGear = gears[1].getEastVal() != gears[2].getWestVal();
        boolean thirdGear = gears[2].getEastVal() != gears[3].getWestVal();

        //홀 양 시, 음 짝
        int directionVal = gearNum * dir;

        if ((directionVal > 0 && (directionVal & 1) == 1) || (directionVal < 0 && (directionVal & 1) == 0)) {
            if (gearNum == 1) {
                gears[0].rotate(dir);

                if (firstGear) {
                    gears[1].rotate(dir*-1);
                }
                else {
                    return;
                }

                if (secondGear) {
                    gears[2].rotate(dir);
                }
                else {
                    return;
                }

                if (thirdGear) {
                    gears[3].rotate(dir*-1);
                }
                else {
                    return;
                }
            }

            else if (gearNum == 2) {
                gears[1].rotate(dir);

                if (firstGear) {
                    gears[0].rotate(dir*-1);
                }


                if (secondGear) {
                    gears[2].rotate(dir * -1);
                }
                else {
                    return;
                }

                if (thirdGear) {
                    gears[3].rotate(dir);
                }
                else {
                    return;
                }
            }
            else if (gearNum == 3) {
                gears[2].rotate(dir);

                if (thirdGear) {
                    gears[3].rotate(dir * -1);
                }

                if (secondGear) {
                    gears[1].rotate(dir * -1);
                }
                else {
                    return;
                }

                if (firstGear) {
                    gears[0].rotate(dir);
                }
            }

            else if (gearNum == 4) {
                gears[3].rotate(dir);

                if (thirdGear) {
                    gears[2].rotate(dir * -1);
                } else {
                    return;
                }

                if (secondGear) {
                    gears[1].rotate(dir);
                } else {
                    return;
                }

                if (firstGear) {
                    gears[0].rotate(dir*-1);
                }
            }
        }
        //홀 음 반, 양 짝
        else {
            if (gearNum == 1) {
                gears[0].rotate(dir);

                if (firstGear) {
                    gears[1].rotate(dir*-1);
                }
                else {
                    return;
                }

                if (secondGear) {
                    gears[2].rotate(dir);
                }
                else {
                    return;
                }

                if (thirdGear) {
                    gears[3].rotate(dir*-1);
                }
                else {
                    return;
                }
            }

            else if (gearNum == 2) {
                gears[1].rotate(dir);

                if (firstGear) {
                    gears[0].rotate(dir*-1);
                }


                if (secondGear) {
                    gears[2].rotate(dir * -1);
                }
                else {
                    return;
                }

                if (thirdGear) {
                    gears[3].rotate(dir);
                }
                else {
                    return;
                }
            }
            else if (gearNum == 3) {
                gears[2].rotate(dir);

                if (thirdGear) {
                    gears[3].rotate(dir * -1);
                }

                if (secondGear) {
                    gears[1].rotate(dir * -1);
                }
                else {
                    return;
                }

                if (firstGear) {
                    gears[0].rotate(dir);
                }
            }

            else if (gearNum == 4) {
                gears[3].rotate(dir);

                if (thirdGear) {
                    gears[2].rotate(dir * -1);
                } else {
                    return;
                }

                if (secondGear) {
                    gears[1].rotate(dir);
                } else {
                    return;
                }

                if (firstGear) {
                    gears[0].rotate(dir*-1);
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //문제 입력
        Gear[] gear = new Gear[4];
        for (int i = 0; i < 4; i++) {
            String gears = sc.next();
            gear[i] = new Gear(makeGear(gears));
        }

        int cnt = sc.nextInt();//횟수
        GearRotator rotator = new GearRotator(gear);
        for (int i = 0; i < cnt; i++) {
            int gearNum = sc.nextInt();
            int dir = sc.nextInt();

            rotator.rotate(gearNum, dir);
        }

        int sum = 0;

        for (int i = 0; i < 4; i++) {
            sum += gear[i].getNorthVal() * (1 << i);
        }
        System.out.println(sum);
    }

    private static int[] makeGear(String str) {
        String[] split = str.split("");
        int[] gear = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            gear[i] = Integer.parseInt(split[i]);
        }
        return gear;
    }
}