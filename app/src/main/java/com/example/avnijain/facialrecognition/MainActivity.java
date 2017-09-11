package com.example.avnijain.facialrecognition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

///////////////
//             *************  CODE 1 starts***********
/// ///////////

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        ImageView myImageView = (ImageView) findViewById(R.id.imgview);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable=true;
        Bitmap myBitmap = BitmapFactory.decodeResource(
                getApplicationContext().getResources(),
                R.drawable.test2,
                options);

        Paint myRectPaint = new Paint();
        myRectPaint.setStrokeWidth(5);
        myRectPaint.setColor(Color.RED);
        myRectPaint.setStyle(Paint.Style.STROKE);

        Bitmap tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(), myBitmap.getHeight(), Bitmap.Config.RGB_565);
        Canvas tempCanvas = new Canvas(tempBitmap);
        tempCanvas.drawBitmap(myBitmap, 0, 0, null);

        FaceDetector faceDetector = new
                FaceDetector.Builder(getApplicationContext())
                .setTrackingEnabled(false)
                .build();
        if(!faceDetector.isOperational()){
          //  new AlertDialog.Builder(setMessage("Could not set up the face detector!").show();
            return;
        }
        Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
        SparseArray<Face> faces = faceDetector.detect(frame);
        for(int i=0; i<faces.size(); i++) {
            Face thisFace = faces.valueAt(i);
            float x1 = thisFace.getPosition().x;
            float y1 = thisFace.getPosition().y;
            float x2 = x1 + thisFace.getWidth();
            float y2 = y1 + thisFace.getHeight();
            tempCanvas.drawRoundRect(new RectF(x1, y1, x2, y2), 2, 2, myRectPaint);
        }
        myImageView.setImageDrawable(new BitmapDrawable(getResources(),tempBitmap));

    ////////////////
    ///             ***********  CODE 1 ENDS  ***************
    ///////////////




        ////////////////
        ///             ***********  EXTRA  CODE   ***************
        ///////////////

//        FaceDetector detector = new FaceDetector.Builder(context)
//                .setTrackingEnabled(false)
//                .setLandmarkType(FaceDetector.ALL_LANDMARKS)
//                .build();
//        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
//        SparseArray<FaceDetector.Face> faces = detector.detect(frame);
//        for (int i = 0; i < faces.size(); ++i) {
//            FaceDetector.Face face = faces.valueAt(i);
//            for (Landmark landmark : face.getLandmarks()) {
//                int cx = (int) (landmark.getPosition().x * scale);
//                int cy = (int) (landmark.getPosition().y * scale);
//                canvas.drawCircle(cx, cy, 10, paint);
//            }
//        }
//
//        FaceDetector detector = new FaceDetector.Builder()
//                .build(getApplicationContext());
//        detector.setProcessor(
//                new MultiProcessor.Builder<FaceDetector.Face>()
//                        .build(new GraphicFaceTrackerFactory()));
//
//        mCameraSource = new CameraSource.Builder()
//                .setRequestedPreviewSize(640, 480)
//                .setFacing(CameraSource.CAMERA_FACING_BACK)
//                .setRequestedFps(30.0f)
//                .build(getApplicationContext(), detector);
//        mPreview.start(mCameraSource, mGraphicOverlay);
//
//        private class GraphicFaceTrackerFactory
//                implements MultiProcessor.Factory<Face> {
//            @Override
//            public Tracker<Face> create(Face face) {
//                return new GraphicFaceTracker(mGraphicOverlay);
//            }
//        }
////
//        private class GraphicFaceTracker extends Tracker<Face> {
//            // other stuff
//
//            @Override
//            public void onNewItem(int faceId, Face face) {
//                mFaceGraphic.setId(faceId);
//            }
//
//            @Override
//            public void onUpdate(FaceDetector.Detections<Face> detectionResults,
//                                 Face face) {
//                mOverlay.add(mFaceGraphic);
//                mFaceGraphic.updateFace(face);
//            }
//
//            @Override
//            public void onMissing(FaceDetector.Detections<Face> detectionResults) {
//                mOverlay.remove(mFaceGraphic);
//            }
//
//            @Override
//            public void onDone() {
//                mOverlay.remove(mFaceGraphic);
//            }
//        }
   }
}
