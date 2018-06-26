#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Tue Jun 26 20:38:03 2018

@author: lavishgambhir
"""

# simple regression problem
import tensorflow as tf
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

# create the dataset
x_data = np.linspace(0.0, 10, 100000)
noise = np.random.random(len(x_data))
b = 5
y_true = (0.5 * x_data) + 5 + noise

x_df = pd.DataFrame(data=x_data, columns=['X Data'])
y_df = pd.DataFrame(data=y_true, columns=['Y'])

data = pd.concat([x_df, y_df], axis=1)
data.sample(n=250).plot(kind='scatter', x='X Data', y='Y')

# The more data the better
# But you can't just feed a million
# points to your model
# So, create batches.
batch_size = 8
m = tf.Variable(0.81)
b = tf.Variable(0.17)
x_pl = tf.placeholder(tf.float32, shape=[batch_size])
y_pl = tf.placeholder(tf.float32, shape=[batch_size])
y_model = m * x_pl + b

error = tf.reduce_sum(tf.square(y_pl-y_model))
optimizer = tf.train.GradientDescentOptimizer(learning_rate = 0.001)
train = optimizer.minimize(error)

init = tf.global_variables_initializer()
with tf.Session() as sess:
    sess.run(init)
    batches = 1000
    
    for i in range(batches):
        rand_in = np.random.randint(len(x_data), size=batch_size)
        feed = {x_pl: x_data[rand_in], y_pl: y_true[rand_in]}
        sess.run(train, feed_dict=feed)
        
    model_m, model_b = sess.run([m, b])

print(model_m, model_b)  
y_hat = x_data * model_m + model_b 
data.sample(250).plot(kind='scatter', x='X Data', y='Y')
plt.plot(x_data, y_hat, 'r')

    



