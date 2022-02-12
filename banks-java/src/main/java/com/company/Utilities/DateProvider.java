package main.java.com.company.Utilities;

public class DateProvider {
    public int Year;
    public int Month;
    public int Day;

    public DateProvider(int d, int m, int y)
    {
        Day = d - 1;
        Month = m;
        Year = y;

        AddOneDay();
    }

    public int GetYear(){
        return Year;
    }

    public void SetYear(){
         this.Year = Year;
    }

    public int GetMont(){
        return Month;
    }

    public void SetMonth(){
        this.Month = Month;
    }

    public int GetDay(){
        return Day;
    }

    public void SetDay(){
        this.Day = Day;
    }

    public void AddOneDay()
    {
        Day++;
        if (Day >= 32)
        {
            Day = 1;
            Month++;
        }

        if (Month >= 13)
        {
            Month = 1;
            Year++;
        }

        if (Day == 31 && (Month == 4 || Month == 6 || Month == 9 || Month == 11))
        {
            Day = 1;
            Month++;
        }

        if (Day == 30 && Month == 2)
        {
            Day = 1;
            Month++;
        }

        if (Day == 29 && Month == 2 && (Year % 4) != 0)
        {
            Day = 1;
            Month++;
        }
    }

    public void Show()
    {
        System.out.println(Day + "." + Month + "." + Year);
    }
}
