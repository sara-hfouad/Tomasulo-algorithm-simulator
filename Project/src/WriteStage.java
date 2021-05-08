import java.util.ArrayList;

public class WriteStage {

	// static ArrayList<String> execute=InstructionMemory.execute;
	// static ArrayList<String> writeBack=InstructionMemory.writeBack;
	// static ArrayList<String> issue = InstructionMemory.issue;

	// static Registers[] register = InstructionMemory.register;
	// static AdderBuffer [] adderBuffer=InstructionMemory.adderBuffer;
	// static MultiplierBuffer []
	// multiplierBuffer=InstructionMemory.multiplierBuffer;
	// static StoreBuffer [] storeBuffer= InstructionMemory.storeBuffer;
	// static LoadBuffer [] loadBuffer= InstructionMemory.loadBuffer;

	public static void writeStage() {
		boolean f = false;
		for (int i = 0; i < InstructionMemory.writeBack.size(); i++) {
			
			for (int j = 0; j < InstructionMemory.adderBuffer.length; j++) {
				if (InstructionMemory.adderBuffer[j] != null) {
					if (InstructionMemory.writeBack.get(i).equals(InstructionMemory.adderBuffer[j].inst)
							&& f == false) {
						if(InstructionMemory.adderBuffer[j].wait2==false) {
							InstructionMemory.adderBuffer[j].wait2=true;
						f = true;
						String name = InstructionMemory.adderBuffer[j].name;

						for (int k = 0; k < InstructionMemory.register.length; k++) {
							if ( InstructionMemory.register[k].qi !=null && InstructionMemory.register[k].qi.equals(name)) {
								// write output value to register value
								InstructionMemory.register[k].qi = "0";
							}
						}
						writeHelper(name);
						System.out.println("The instruction " + InstructionMemory.writeBack.get(i)
						+ " was written at cycle " + InstructionMemory.cycle);
						InstructionMemory.adderBuffer[j].wait=true;
						}else {
						InstructionMemory.adderBuffer[j] = null;
						
						
						}
						//break;
					}
				}
			}

			for (int j = 0; j < InstructionMemory.multiplierBuffer.length; j++) {
				if (InstructionMemory.multiplierBuffer[j] != null) {
					if (InstructionMemory.writeBack.get(i).equals(InstructionMemory.multiplierBuffer[j].inst)
							&& f == false) {
						if(InstructionMemory.multiplierBuffer[j].wait2==false) {
							InstructionMemory.multiplierBuffer[j].wait2=true;
						f = true;
						String name = InstructionMemory.multiplierBuffer[j].name;
						for (int k = 0; k < InstructionMemory.register.length; k++) {
							if (InstructionMemory.register[k].qi !=null && InstructionMemory.register[k].qi.equals(name)) {
								// write output value to register value
								InstructionMemory.register[k].qi = "0";
							}
						}
						writeHelper(name);
						System.out.println("The instruction " + InstructionMemory.writeBack.get(i)
						+ " was written at cycle " + InstructionMemory.cycle);
						InstructionMemory.multiplierBuffer[j].wait=true;}
						else {
						InstructionMemory.multiplierBuffer[j] = null;
						}

					}
				}
			}

			for (int j = 0; j < InstructionMemory.storeBuffer.length; j++) {
				if (InstructionMemory.storeBuffer[j] != null) {
					if (InstructionMemory.writeBack.get(i).equals(InstructionMemory.storeBuffer[j].inst)
							&& f == false) {
						if(InstructionMemory.storeBuffer[j].wait2==false) {
							InstructionMemory.storeBuffer[j].wait2=true;
						f = true;
						String name = InstructionMemory.storeBuffer[j].name;
						for (int k = 0; k < InstructionMemory.register.length; k++) {
							if (InstructionMemory.register[k].qi.equals(name)) {
								InstructionMemory.register[k].qi = "0";
								// write output value to register value
							}
						}
						writeHelper(name);
						System.out.println("The instruction " + InstructionMemory.writeBack.get(i)
						+ " was written at cycle " + InstructionMemory.cycle);
						InstructionMemory.storeBuffer[j].wait=true;}
						else {
						InstructionMemory.storeBuffer[j] = null;
						}

					}
				}
			}

			//

			for (int j = 0; j < InstructionMemory.loadBuffer.length; j++) {
				if (InstructionMemory.loadBuffer[j] != null) {
					if (InstructionMemory.writeBack.get(i).equals(InstructionMemory.loadBuffer[j].inst) && f == false) {
						if(InstructionMemory.loadBuffer[j].wait2==false) {
							InstructionMemory.loadBuffer[j].wait2=true;
						f = true;
						String name = InstructionMemory.loadBuffer[j].name;
						for (int k = 0; k < InstructionMemory.register.length; k++) {
							if (InstructionMemory.register[k].qi != null) {
								if (InstructionMemory.register[k].qi.equals(name)) {
									// write output value to register value
									InstructionMemory.register[k].qi = "0";
								}
							}
						}
						writeHelper(name);
						System.out.println("The instruction " + InstructionMemory.writeBack.get(i)
						+ " was written at cycle " + InstructionMemory.cycle);
						}
						
						
						else {
							InstructionMemory.loadBuffer[j] = null;
						
						}
					}
				}
			}
		}

		// flag to start writing back
	}

	public static void writeHelper(String name) {

		for (int i = 0; i < InstructionMemory.issue.size(); i++) {

			for (int j = 0; j < InstructionMemory.adderBuffer.length; j++) {
				if (InstructionMemory.adderBuffer[j] != null) {
					if (InstructionMemory.adderBuffer[j].inst.equals(InstructionMemory.issue.get(i))) {
					
						if (InstructionMemory.adderBuffer[j].qj != null
								&& InstructionMemory.adderBuffer[j].qj.equals(name)) {
							// let vj equals output value
							//System.out.println("here");
							InstructionMemory.adderBuffer[j].wait=true;
							InstructionMemory.adderBuffer[j].vj = "0";
							InstructionMemory.adderBuffer[j].qj = null;
						}
						if (InstructionMemory.adderBuffer[j].qk != null
								&& InstructionMemory.adderBuffer[j].qk.equals(name)) {
							// let vk equals output value
							//System.out.println("here");
							InstructionMemory.adderBuffer[j].wait=true;
							InstructionMemory.adderBuffer[j].vk = "0";
							InstructionMemory.adderBuffer[j].qk = null;
						}
					}
				}
			}

			for (int j = 0; j < InstructionMemory.multiplierBuffer.length; j++) {
				if (InstructionMemory.multiplierBuffer[j] != null) {
					if (InstructionMemory.multiplierBuffer[j].inst.equals(InstructionMemory.issue.get(i))) {
						//InstructionMemory.multiplierBuffer[j].wait=true;
						if (InstructionMemory.multiplierBuffer[j].qj != null
								&& InstructionMemory.multiplierBuffer[j].qj.equals(name)) {
							// let vj equals output value
							//System.out.println("here");
							InstructionMemory.multiplierBuffer[j].wait=true;
							InstructionMemory.multiplierBuffer[j].vj = "0";
							InstructionMemory.multiplierBuffer[j].qj = null;
						}
						if (InstructionMemory.multiplierBuffer[j].qk != null
								&& InstructionMemory.multiplierBuffer[j].qk.equals(name)) {
							// let vk equals output value
							//System.out.println("here");
							InstructionMemory.multiplierBuffer[j].wait=true;
							InstructionMemory.multiplierBuffer[j].vk = "0";
							InstructionMemory.multiplierBuffer[j].qk = null;
						}
					}
				}
			}

			for (int j = 0; j < InstructionMemory.storeBuffer.length; j++) {
				if (InstructionMemory.storeBuffer[j] != null) {
					if (InstructionMemory.storeBuffer[j].inst.equals(InstructionMemory.issue.get(i))) {
						//InstructionMemory.storeBuffer[j].wait=true;
						if (InstructionMemory.storeBuffer[j].qj != null
								&& InstructionMemory.storeBuffer[j].qj.equals(name)) {
							// let vj equals output value
							InstructionMemory.storeBuffer[j].wait=true;
							InstructionMemory.storeBuffer[j].vj = "0";
							InstructionMemory.storeBuffer[j].qj = null;
						}

					}
				}
			}

			//

		}
	}

}
