package productlab.atp.touchscreentest_v1;

import java.util.ArrayList;

/**
 * Created by Chelsea on 5/4/2016.
 */
public class Globals //extends Application
{
    private static Globals instance;
    private int masterIndex;            //  Index that increases by 1 each time a new UI setup is presented
    private String subjectId;           //  ID unique to each subject (entered by admin in setup screen)
    private String adminId;             //  ID unique to each administrator (usually first name)
    private String dimensions;          //  Set of dimensions input to create each UI screen
    private String triggeredListener;   //  Indicates which listener was activated (where user pressed)
    private Boolean practice;           //  True for practice activity; false for main activity
    private Integer squareDim;
    private Integer imgDim;
    private Integer touchDim;
    private Integer delegateDim;
    private Float ratio;
    private Integer activeButton;
    private Float spaceDim;
    private Float size;

    private ArrayList<Double> imgDimArray = new ArrayList<>();              //  All button dimensions to be used
    private ArrayList<Double> spaceDimArray = new ArrayList<>();            //  All spacing dimensions to be used
    private ArrayList<Double> ratioTouchImgArray = new ArrayList<>();       //  All ratios to be used
    private ArrayList<Integer> activeButtonArray = new ArrayList<>();       //  Array of 1-4 used to choose active button
    private ArrayList<Integer> randomActiveButtonsArray = new ArrayList<>();//  Integers 1-4 in random order for each task
    private double numUIVars = 3.;                                              //  The number of variables changed on UI
    private double numVarLevels = 4.;                                           //  The number of levels of each UI variable
    private int numReps = 3;                                                    //  The number of replicates to perform
    private int numCombos = (int) (Math.pow(numVarLevels, numUIVars));           //  Number of combinations to be presented
    private Double[][] fullDataArray = new Double[numCombos * numReps][(int) numUIVars];//  All permutations of raw data arrays, x3
    private ArrayList<Integer> randomOrderArray = new ArrayList<>();        //  Array of integers determining random order of tasks


    private Globals() {
    }

    //  Creates an instance of the Globals class if none exists.
    //  If an instance already exists, returns that instance.
    //  This method allows values of variables to be saved and used in different methods thorughout the life cycle.
    public static synchronized Globals getInstance() {
        if (instance == null) {
            instance = new Globals();
        }
        return instance;
    }

    //  Sets variable randomOrderButtons using array passed in from Start Activity
    public void setRandomActiveButtonsArray(ArrayList<Integer> randomOrderButtons) {
        randomActiveButtonsArray = randomOrderButtons;
    }

    //  Returns active button index given a certain masterIndex
    public int getActiveButton(int index) {
        return randomActiveButtonsArray.get(index);
    }

    public void setActiveButton(int active_button) {
        activeButton = active_button;
    }

    public int getActiveButtonValue() {
        return activeButton;
    }

    //  Returns random order array
    public ArrayList<Integer> getRandomOrder() {
        return randomOrderArray;
    }

    //  Sets variable randomOrder using array passed in from Start Activity
    public void setRandomOrder(ArrayList<Integer> randomOrder) {
        randomOrderArray = randomOrder;
    }

    //  Returns element from randomOrderArray given the current master index
    public Integer getRandomOrderElement(int index) {
        return randomOrderArray.get(index);
    }

    //  Increases the masterIndex by 1 (after each task)
    public void addToIndex() {
        masterIndex++;
    }

    //  Returns the current value of the master index
    public Integer getMasterIndex() {
        return masterIndex;
    }

    //  Sets the value of the masterIndex
    public void setMasterIndex(int mastInd) {
        masterIndex = mastInd;
    }

    //  Sets raw data to be used
    public void setImgDimArray() {

        imgDimArray.add(40.945);
        imgDimArray.add(56.693);
        imgDimArray.add(72.441);
        imgDimArray.add(88.189);
    }

    //  Sets raw data to be used
    public void setSpaceDimArray() {
        spaceDimArray.add(4.724);
        spaceDimArray.add(9.449);
        spaceDimArray.add(14.173);
        spaceDimArray.add(18.898);
    }

    //  Sets raw data to be used
    public void setRatioTouchImgArray() {
        ratioTouchImgArray.add(1.05);
        ratioTouchImgArray.add(1.10);
        ratioTouchImgArray.add(1.15);
        ratioTouchImgArray.add(1.20);
    }

    //  Sets raw data to be used
    public void setActiveButtonArray() {
        activeButtonArray.add(1);
        activeButtonArray.add(2);
        activeButtonArray.add(3);
        activeButtonArray.add(4);
    }

    //  Permutates all combinations of imgDim, spaceDim, and ratio in triplicate
    public void setFullDataArray() {
        int y = 0;

        for (int z = 0; z < 3; z++) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 4; k++) {
                        fullDataArray[y][0] = imgDimArray.get(i);
                        fullDataArray[y][1] = spaceDimArray.get(j);
                        fullDataArray[y][2] = ratioTouchImgArray.get(k);
                        y++;
                    }
                }
            }
        }
    }

    //  Returns specific element of the permutated array
    public Double getFullDataArrayElement(Integer row, Integer col) {
        return fullDataArray[row][col];
    }

    //  Returns the subject ID
    public String getSubjectId() {
        return subjectId;
    }

    //  Sets global variable subjectId (used to draw value from input box in StartActivity)
    public void setSubjectId(String subId) {
        subjectId = subId;
    }

    //  Returns the admin ID
    public String getAdminId() {
        return adminId;
    }

    //  Sets global variable adminId (used to draw value from input box in StartActivity)
    public void setAdminId(String admId) {
        adminId = admId;
    }

    //  Returns the currently triggered listener
    public String getTriggeredListener() {
        return triggeredListener;
    }

    //  Sets the value for the listener triggered by a touch event to be referenced by another class
    public void setTriggeredListener(String trigListener) {
        triggeredListener = trigListener;
    }

    //  Returns/Sets the current value of the squareDim
    public Integer getSquareDim() {
        return squareDim;
    }

    public void setSquareDim(Integer square_dim) {
        squareDim = square_dim;
    }

    //  Returns/Sets the current value of the imgDim
    public Integer getImgDim() {
        return imgDim;
    }

    public void setImgDim(Integer img_dim) {
        imgDim = img_dim;
    }

    //  Returns/Sets the current value of the touchDim
    public Integer getTouchDim() {
        return touchDim;
    }

    public void setTouchDim(Integer touch_dim) {
        touchDim = touch_dim;
    }

    //  Returns/Sets the current value of the delegateDim
    public Integer getDelegateDim() {
        return delegateDim;
    }

    public void setDelegateDim(Integer delegate_dim) {
        delegateDim = delegate_dim;
    }

    //  Returns/Sets the current value of the ratio
    public Float getRatio() {
        return ratio;
    }

    public void setRatio(Float _ratio) {
        ratio = _ratio;
    }

    //  Returns/Sets the current value of the spaceDim
    public Float getSpaceDim() {
        return spaceDim;
    }

    public void setSpaceDim(Float space_dim) {
        spaceDim = space_dim;
    }

    //  Returns/Sets the current value of the size
    public Float getSize() {
        return size;
    }

    public void setSize(Float size_dim) {
        size = size_dim;
    }

    //  Returns the current value of the string 'dimensions'
    public String getDimensions() {
        return dimensions;
    }

    //  Sets the global variable 'dimensions' given current UI specs
    public void setDimensions(String dims) {
        dimensions = dims;
    }

    //  Returns the current value of the boolean 'practice'
    public Boolean getPractice() {
        return practice;
    }

    //  Sets the global variable 'practice'
    public void setPractice(Boolean setPract) {
        practice = setPract;
    }
}
