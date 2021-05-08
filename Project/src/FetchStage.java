
public class FetchStage {

	public static void fetchStage() {
		if(!InstructionMemory.instructions.isEmpty()) {
    String ins= InstructionMemory.instructions.get(0);
    InstructionMemory.instructions.remove(0);
    InstructionMemory.fetch.add(ins);
    System.out.println("The instruction " + ins + " was fetched at cycle " + InstructionMemory.cycle );
    
		}
	}
}
