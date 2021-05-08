import java.util.ArrayList;
import java.util.Arrays;

public class InstructionMemory {
	// takes 6 cycles and addition takes 4 cycles,
	public static ArrayList<String> instructions = new ArrayList<>();
	// public static ArrayList<String> state = new ArrayList<>();
	public static ArrayList<String> fetch = new ArrayList<>();
	public static ArrayList<String> issue = new ArrayList<>();
	public static ArrayList<String> execute = new ArrayList<>();
	public static ArrayList<String> writeBack = new ArrayList<>();

	public static AdderBuffer[] adderBuffer = new AdderBuffer[3];
	public static MultiplierBuffer[] multiplierBuffer = new MultiplierBuffer[2];
	public static StoreBuffer[] storeBuffer = new StoreBuffer[3];
	public static LoadBuffer[] loadBuffer = new LoadBuffer[3];
	// public static ArrayList<Registers> register = new ArrayList<>();
	public static Registers[] register = new Registers[11];

	public static int cycle = 1;

	public static boolean adder() {
		for (int i = 0; i < adderBuffer.length; i++) {
			if (adderBuffer[i] != null)
				return true;
		}
		return false;
	}

	public static boolean mul() {
		for (int i = 0; i < multiplierBuffer.length; i++) {
			if (multiplierBuffer[i] != null)
				return true;
		}
		return false;
	}

	public static boolean store() {
		for (int i = 0; i < storeBuffer.length; i++) {
			if (storeBuffer[i] != null)
				return true;
		}
		return false;
	}

	public static boolean load() {
		for (int i = 0; i < loadBuffer.length; i++) {
			if (loadBuffer[i] != null)
				return true;
		}
		return false;
	}

	public static void tomasulo() {
		while (!instructions.isEmpty() || !fetch.isEmpty() || adder() || mul() || load() || store()) {
			System.out.println("CYCLE NUMBER: "+cycle);
			WriteStage.writeStage();
			ExecuteStage.executeStage();
			IssueStage.issueStage();
			FetchStage.fetchStage();
			//System.err.println(InstructionMemory.execute.size());
		
			System.out.println(" ...............................");

			cycle++;

		}
	}

	public static void main(String[] args) {
		// FILL INSTRUCTIONS

		instructions.add("MUL R3 R1 R2");
		instructions.add("ADD R5 R3 R4");
		instructions.add("ADD R7 R2 R6");
		instructions.add("ADD R10 R8 R9");
		instructions.add("MUL R11 R7 R10");
		instructions.add("ADD R5 R5 R11");
      //FILL THE REGISTER
		Registers a = new Registers();
		a.rname = "R1";
		register[0] = a;
		Registers b = new Registers();
		b.rname = "R2";
		register[1] = b;
		Registers c = new Registers();
		c.rname = "R3";
		register[2] = c;
		Registers d = new Registers();
		d.rname = "R4";
		register[3] = d;
		Registers e = new Registers();
		e.rname = "R5";
		register[4] = e;
		Registers f = new Registers();
		f.rname = "R6";
		register[5] = f;
		Registers g = new Registers();
		
		g.rname = "R7";
		register[6] = g;
		Registers h = new Registers();
		h.rname = "R8";
		register[7] = h;
		Registers i = new Registers();
		i.rname = "R9";
		register[8] = i;
		Registers j = new Registers();
		j.rname = "R10";
		register[9] = j;
		Registers k = new Registers();
		k.rname = "R11";
		register[10] = k;



		tomasulo();

	}

}
