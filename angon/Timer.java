
package angon;
public class Timer {
    private long gameTimeH,gameTimeM,gameTimeS;
    public Timer(long gameTimeH,long gameTimeM ,long gameTimeS)
    {
    this.gameTimeH=gameTimeH;
    this.gameTimeM=gameTimeM;
    this.gameTimeS=gameTimeS;
    }
    //<editor-fold defaultstate="collapsed" desc="Get/Set">
    public void setHour(long gameTimeH)
    {
        this.gameTimeH=gameTimeH;
    }
    public void setMinutes(long gameTimeM)
    {
        this.gameTimeM=gameTimeM;
    }
    public void setSeconds(long gameTimeS)
    {
        this.gameTimeS=gameTimeS;
    }
        public long getMinutes()
    {   
    return gameTimeM;
    }
    public long getHours()
    {   
    return gameTimeH;
    }
    public long getSecodns()
    {   
    return gameTimeS;
    }
    public String getTime()
    {
        if(gameTimeM<1)
        {
            if(gameTimeS<10)
                return "0"+gameTimeS;
            else return ""+gameTimeS;
        }
        
        else if(gameTimeH<1)
        {
            if(gameTimeM<10)
            {
                if(gameTimeS<10)
                    return "0"+gameTimeM+":0"+gameTimeS;
                else return "0"+gameTimeM+":"+gameTimeS;
            }
            else 
            {
                if(gameTimeS<10)
                    return gameTimeM+":0"+gameTimeS;
                else return gameTimeM+":"+gameTimeS;
            }
        }
        
        else if(gameTimeH>1)
        {
            if(gameTimeM<10)
            {
                if(gameTimeS<10)
                    return gameTimeH+":0"+gameTimeM+":0"+gameTimeS;
                else return gameTimeH+":0"+gameTimeM+":"+gameTimeS;
            }
            else 
            {
                if(gameTimeS<10)
                    return gameTimeH+":"+gameTimeM+":0"+gameTimeS;
                else return gameTimeH+":"+gameTimeM+":"+gameTimeS;
            }
        }
        else return "";
    }
//</editor-fold>


}
