let apm = undefined
if ('ELASTIC_APM_SECRET_TOKEN' in process.env &&
    'ELASTIC_APM_SERVER_URL' in process.env &&
    'ELASTIC_APM_SERVICE_NAME' in process.env ) {
  apm = require('elastic-apm-node').start()
}

const express = require('express')
const app = express()
const port = 3001
const winston = require('winston')
const ecsFormat = require('@elastic/ecs-winston-format')


const {
  createLightship
} = require('lightship')

// Lightship will start a HTTP service on port 9000.
const lightship = createLightship()

const env = process.env.NODE_ENV

const logger = winston.createLogger({
  format: env !== undefined && env.includes('k8s') ?
      ecsFormat() : winston.format.simple(),
  transports: [
    new winston.transports.Console()
  ]
})

class Stock {
  constructor(ticker, price) {
    this.ticker = ticker;
    this.price = price;
  }
}

let validTill = Date.now() + 10000

function sleep(millis) {
  return new Promise(resolve => setTimeout(resolve, millis))
}

function randomInteger(min, max) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

let apple = new Stock('AAPL', randomInteger(80,180))
let google = new Stock('GOOGL', randomInteger(1500, 2700))
let  microsoft = new Stock('MSFT', randomInteger(180,300))

app.get('/stocks', (req, res) => {

  let span = undefined
  if(apm !== undefined)
    span = apm.startSpan('stock-calculation')

  sleep(1000).then(() => {
     logger.info("Getting stock values, always takes 1 second")

     if(validTill < Date.now()) {
        apple.price = randomInteger(80,180); 
        google.price = randomInteger(1500, 2700)
        microsoft.price = randomInteger(180,300)
        
        validTill = Date.now() + (10 * 1000)

        logger.info("Got fresh stock prices") 

     }else {
       logger.info("Got cached stock prices")
     }
     if(span) span.end()
   
     res.setHeader('Content-Type', 'application/json')
     let arrayOfStocks = [apple, google, microsoft]

     const dictOfStocks = Object.fromEntries(arrayOfStocks.map(e => [e.ticker, e.price])
)
     res.set('Cache-Control', 'public, max-age=' + Math.round(validTill / 1000))
     return res.send(dictOfStocks)

    });
  
})


app.listen(port, () => {
  lightship.signalReady()
  logger.info(`ECCNodejs is listening on port ${port}`)
})
