import java.util.ArrayList;

public class ExecuteStage {

	public static void executeStage() {

		for (int i = 0; i < InstructionMemory.adderBuffer.length; i++) {

			if (InstructionMemory.adderBuffer[i] != null) {
				if (InstructionMemory.adderBuffer[i].vj != null && InstructionMemory.adderBuffer[i].vk != null) {
					// remove from issue array
					for (int j = 0; j < InstructionMemory.issue.size(); j++) {
						if (InstructionMemory.adderBuffer[i].inst.equals(InstructionMemory.issue.get(j))) {
							InstructionMemory.issue.remove(j);
							break;
						}
					}
					boolean f1 = false;
					for (int j = 0; j < InstructionMemory.execute.size(); j++) {
						if (InstructionMemory.execute.get(j).equals(InstructionMemory.adderBuffer[i].inst))
							f1 = true;
					}
					if (!f1 && InstructionMemory.adderBuffer[i].wait == false && InstructionMemory.adderBuffer[i].time>0) {
						InstructionMemory.execute.add(InstructionMemory.adderBuffer[i].inst);
						//InstructionMemory.adderBuffer[i].time = 4;
					} else {
						InstructionMemory.adderBuffer[i].wait = false;
					}
					// add to execute array

//					System.out.println("The instruction " + InstructionMemory.adderBuffer[i].inst
//							+ " is executing at cycle " + InstructionMemory.cycle + " time left "+InstructionMemory.adderBuffer[i].time);

//					for (int j2 = 0; j2 < InstructionMemory.execute.size(); j2++) {
//						System.out.println(InstructionMemory.execute.get(j2));
//						
//					}
				}
			}

		}
		for (int i = 0; i < InstructionMemory.multiplierBuffer.length; i++) {
			if (InstructionMemory.multiplierBuffer[i] != null) {
				if (InstructionMemory.multiplierBuffer[i].inst != null) {
					// System.out.println("MultiplierBuffer: " +
					// InstructionMemory.multiplierBuffer[i].inst);
				}
			}
			if (InstructionMemory.multiplierBuffer[i] != null) {
				if (InstructionMemory.multiplierBuffer[i].vj != null
						&& InstructionMemory.multiplierBuffer[i].vk != null) {
					// InstructionMemory.multiplierBuffer[i].time = 10;
					// remove from issue array
					for (int j = 0; j < InstructionMemory.issue.size(); j++) {

						if (InstructionMemory.multiplierBuffer[i].inst.equals(InstructionMemory.issue.get(j))) {
							InstructionMemory.issue.remove(j);
							break;
						}
					}
					// add to execute array
					boolean f = false;
					for (int j = 0; j < InstructionMemory.execute.size(); j++) {
						if (InstructionMemory.execute.get(j).equals(InstructionMemory.multiplierBuffer[i].inst))
							f = true;
					}
					if (!f && InstructionMemory.multiplierBuffer[i].wait == false && InstructionMemory.multiplierBuffer[i].time>0) {
						InstructionMemory.execute.add(InstructionMemory.multiplierBuffer[i].inst);
						//InstructionMemory.multiplierBuffer[i].time = 6;
					} else {
						InstructionMemory.multiplierBuffer[i].wait = false;
					}

//					System.out.println("The instruction " + InstructionMemory.multiplierBuffer[i].inst
//							+ " is executing at cycle " + InstructionMemory.cycle);
//					

				}
			}
		}
		for (int i = 0; i < InstructionMemory.loadBuffer.length; i++) {
			if (InstructionMemory.loadBuffer[i] != null) {
				// InstructionMemory.loadBuffer[i].time = 2;
				// remove from issue array
				for (int j = 0; j < InstructionMemory.issue.size(); j++) {
					if (InstructionMemory.loadBuffer[i].inst.equals(InstructionMemory.issue.get(j))) {
						InstructionMemory.issue.remove(j);
						break;
					}
				}

				// add to execute array
				InstructionMemory.execute.add(InstructionMemory.loadBuffer[i].inst);

//				System.out.println("The instruction " + InstructionMemory.loadBuffer[i].inst + " is executing at cycle "
//						+ InstructionMemory.cycle);
			}
		}
		for (int i = 0; i < InstructionMemory.storeBuffer.length; i++) {
			if (InstructionMemory.storeBuffer[i] != null) {
				if (InstructionMemory.storeBuffer[i].vj != null) {
					// InstructionMemory.storeBuffer[i].time = 2;
					boolean f = false;
					// remove from issue array
					for (int j = 0; j < InstructionMemory.issue.size(); j++) {
						if (InstructionMemory.storeBuffer[i].inst.equals(InstructionMemory.issue.get(j))) {
							InstructionMemory.issue.remove(j);
							break;
						}
					}

					// add to execute array
					if (!f && InstructionMemory.storeBuffer[i].wait == false && InstructionMemory.storeBuffer[i].time>0) {
						InstructionMemory.execute.add(InstructionMemory.storeBuffer[i].inst);
						//InstructionMemory.multiplierBuffer[i].time = 6;
					} else {
						InstructionMemory.storeBuffer[i].wait = false;
					}
//					System.out.println("The instruction " + InstructionMemory.storeBuffer[i].inst
//							+ " is executing at cycle " + InstructionMemory.cycle);
				}
			}

		}
		for (int i = 0; i < InstructionMemory.execute.size(); i++) {
			// boolean flagR = false;
	
			if (InstructionMemory.execute.get(i) != "") {
				System.out.println("The instruction " + InstructionMemory.execute.get(i) + " is executing at cycle "
						+ InstructionMemory.cycle);
				//System.out.println(i +"    "+InstructionMemory.execute.get(i));
				}

			// dec time in adder buffer

			for (int j = 0; j < InstructionMemory.adderBuffer.length; j++) {
				if (InstructionMemory.adderBuffer[j] != null) {
					if (InstructionMemory.execute.get(i).equals(InstructionMemory.adderBuffer[j].inst)) {

						InstructionMemory.adderBuffer[j].time--;

						// System.out.println(InstructionMemory.adderBuffer[j].time);
						if (InstructionMemory.adderBuffer[j].time == 0) {
//						adderBuffer[j]=null;
							// System.out.println("hyyyy");
							//System.err.println("removedddd " + i+InstructionMemory.execute.get(i));
							InstructionMemory.writeBack.add(InstructionMemory.execute.get(i));
							InstructionMemory.execute.set(i, "");
							//System.out.println("eh el gowa "+InstructionMemory.execute.get(i) );
							// flagR=true;
						}

					}
				}
			}

			for (int j = 0; j < InstructionMemory.multiplierBuffer.length; j++) {
				if (InstructionMemory.multiplierBuffer[j] != null) {
					if (InstructionMemory.execute.get(i).equals(InstructionMemory.multiplierBuffer[j].inst)) {

						InstructionMemory.multiplierBuffer[j].time--;
//						System.out.println("The instruction " + InstructionMemory.execute.get(i)
//						+ " is executing at cycle " + InstructionMemory.cycle);
						// System.out.println(InstructionMemory.multiplierBuffer[j].time);
						if (InstructionMemory.multiplierBuffer[j].time == 0) {
//						multiplierBuffer[j]=null;
							//System.err.println("removedddd " +i+ InstructionMemory.execute.get(i));
							InstructionMemory.writeBack.add(InstructionMemory.execute.get(i));
							InstructionMemory.execute.set(i, "");
							// flagR=true;
						}

					}
				}

			}

			for (int j = 0; j < InstructionMemory.loadBuffer.length; j++) {
				if (InstructionMemory.loadBuffer[j] != null) {
					if (InstructionMemory.execute.get(i).equals(InstructionMemory.loadBuffer[j].inst)) {
						InstructionMemory.loadBuffer[j].time--;
						if (InstructionMemory.loadBuffer[j].time == 0) {
//						loadBuffer[j]=null;
							InstructionMemory.writeBack.add(InstructionMemory.execute.get(i));
							InstructionMemory.execute.set(i, "");
							// flagR = true;
						}
					}
				}
			}

			for (int j = 0; j < InstructionMemory.storeBuffer.length; j++) {
				if (InstructionMemory.storeBuffer[j] != null) {
					if (InstructionMemory.execute.get(i).equals(InstructionMemory.storeBuffer[j].inst)) {
						// if (InstructionMemory.storeBuffer[j].wait == false) {
						InstructionMemory.storeBuffer[j].time--;
						if (InstructionMemory.storeBuffer[j].time == 0) {
//						storeBuffer[j]=null;
							InstructionMemory.writeBack.add(InstructionMemory.execute.get(i));
							InstructionMemory.execute.set(i, "");
							// flagR = true;
						}
					}
//					} else {
//						InstructionMemory.storeBuffer[j].wait = false;
//
//					}
				}
			}

//			if (flagR == true) {
//				InstructionMemory.execute.remove(i);
//				i = i - 1;
//
//			}
		}

		// execute all insts in execute array

	}

}
