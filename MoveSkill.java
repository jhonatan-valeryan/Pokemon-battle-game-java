public class MoveSkill { 

 

private String MoveName; 

private String MoveType; 

private double Power; 

public MoveSkill() { 

 

} 

 

public MoveSkill(String MoveName, String MoveType, double Power) { 

this.MoveName = MoveName; 

this.MoveType = MoveType; 

this.Power = Power; 

} 

 

public String getMoveName() { 

return MoveName; 

} 

 

public String getMoveType() { 

return MoveType; 

} 

 

public double getPower() { 

return Power; 

} 

 

 

@Override 

public String toString() { 

        return String.format("%s | %s", MoveName, MoveType); 

    } 

 

}