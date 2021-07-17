from flask import Flask, request, jsonify
import numpy as np
import os
import io
import base64
import cv2
import PIL.Image as Image
import matplotlib.pyplot as plt
from imageio import imread
from tensorflow.python.keras.models import load_model



app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Check Shoes Model!'

@app.route('/check', methods = ['POST'])
def model_check():
    model = load_model(os.path.join('C:\\Users\\1217s\\Desktop\\shoes recommendation', 'shoes_classifier.h5'), compile=False)
    categories = ['nike_coat_legacy'              
              ,'adidas_equipment10'
              ,'converse_chuckTailor_allstar_classic'
              ,'fila_coatDelux_belcro'
              ,'puma_kaia_platform'
              ,'discovery_jogger_flex'
              ,'reebok_royal_bridge3'
              ,'newbal_w480'
              ,'adidas_superstar'
              ,'nike_revolution5'
              ,'converse_chuckTailor_dainti_mule'
              ,'fila_classic_border_stitch'
              ,'puma_smash_bulk_mule'
              ,'discovery_bucket_dewalker_v2'
              ,'reebok_zig_dynamica'
              ,'newbal_ms237'
              ,'nike_air_tailwind79'
              ,'adidas_responseSR'
              ,'fila_como_mule'
              ,'puma_thunder_passion2.0'
              ,'converse_chuckTailor_move_high'
              ,'discovery_bucket_mountain_LT'
              ,'reebok_energen_run'
              ,'newbal_mr530'
              ,'nike_airmax_infinity2'
              ,'adidas_brabada'
              ,'fila_classic_kicksB'
              ,'puma_future_rider_play_on'
              ,'converse_chuckTailor_allstar_high'
              ,'discovery_brick'
              ,'reebok_royal_run_finish'
              ,'newbal_sd5205'
              ,'nike_coat_legacy_mule'
              ,'adidas_retro_bulk'
              ,'fila_bumper_mule'
              ,'puma_bari_mule'
              ,'prospecs_bigstar101'
              ,'newbal_mt410'
              ,'fila_aztrack96'
              ,'converse_chuckTailor_allStar'
              ,'puma_interplex_runner'
              ,'fila_bumper'
              ,'adidas_grand_coat_base'
              ,'nike_airmax_vg-r'
              ]
    
    requestedJSON = request.get_json()
    

    ByteArray = base64.b64decode(requestedJSON)
    image_pil = Image.open(io.BytesIO(ByteArray))
    image = np.array(image_pil)

    img = image.astype('float32')/255
    img = cv2.resize(img,(300,300))
    img = np.expand_dims(img, 0)

    y_predicted = model.predict(img)

    sortedIndex = np.argsort(y_predicted)[0][::-1]
    np.set_printoptions(suppress=True)
    data = {
        "first_brand" : categories[sortedIndex[0]],
        "second_brand" : categories[sortedIndex[1]],
        "third_brand" : categories[sortedIndex[2]],
        "first_percentage" : str(y_predicted[0][sortedIndex[0]]),
        "second_percentage" : str(y_predicted[0][sortedIndex[1]]),
        "third_percentage" : str(y_predicted[0][sortedIndex[2]])
    }
    print(data)
    return jsonify(data)



if __name__ == '__main__':
    app.run()