//
//  BookGrandImageView.swift
//  iosApp
//
//  Created by Sergey Prybysh on 2/8/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import CoreImage
import CoreImage.CIFilterBuiltins

struct BookGrandImageView: View {
  let bookImage: BookImage
  
  @State private var bottomColor: Color = .clear
  @State private var fullColor: Color = .clear
  
  @State private var loaded: Bool = false
  
  var body: some View {
    VStack(alignment: .leading, spacing: 0) {
      ABAsyncImageView(
        url: URL(string: bookImage.imageURL),
        content: { (image, originalImage) in
          image
            .resizable()
            .frame(width: 200, height: 200)
            .onAppear {
              loaded = true
              extractColors(from: originalImage)
            }
        },
        loading: {
          ProgressView()
        },
        error: {
          FallbackView(
            authorName: bookImage.authorName,
            title: bookImage.title,
            gradientColors: bookImage.gradientColors) }
      )
      if loaded {
        VStack(spacing: 8) {
          Text(bookImage.title)
            .lineLimit(1)
            .font(._subtitle)
            .foregroundStyle(.white)
            .padding(.horizontal, 8)
          
          Text(bookImage.authorName)
            .lineLimit(1)
            .font(._subtitle)
            .foregroundStyle(.white)
            .padding(.horizontal, 8)
        }
        .frame(width: 200)
        .padding(.top, 8)
        .padding(.bottom, 12)
        .background(
          LinearGradient(
            gradient: Gradient(colors: [bottomColor, fullColor]),
            startPoint: .top,
            endPoint: .bottom
          )
        )
      }
    }
    .clipShape(.rect(cornerRadius: 10))
  }
  
  private func extractColors(from image: UIImage) {
    guard let ciImage = CIImage(image: image) else { return }
    
    DispatchQueue.global(qos: .userInitiated).async {
      let fullAvgColor = self.averageColor(from: ciImage)
      let bottomAvgColor = self.averageColor(from: ciImage, bottomOnly: true)
      
      DispatchQueue.main.async {
        self.fullColor = Color(uiColor: fullAvgColor ?? .clear)
        self.bottomColor = Color(uiColor: bottomAvgColor ?? .clear)
      }
    }
  }
  
  private func averageColor(from ciImage: CIImage, bottomOnly: Bool = false) -> UIColor? {
    let context = CIContext()
    let extent = ciImage.extent
    
    let cropRect: CGRect
    if bottomOnly {
      let height = extent.height * 0.1
      cropRect = CGRect(x: extent.origin.x, y: extent.origin.y, width: extent.width, height: height)
    } else {
      cropRect = extent
    }
    
    let croppedImage = ciImage.cropped(to: cropRect).clampedToExtent()
    
    guard let filter = CIFilter(name: "CIAreaAverage") else { return nil }
    
    filter.setValue(croppedImage, forKey: kCIInputImageKey)
    
    guard let outputImage = filter.outputImage,
          let bitmap = context.renderedImage(from: outputImage, extent: outputImage.extent) else { return nil }
    
    let colorComponents = bitmap.map { CGFloat($0) / 255.0 }
    return UIColor(red: colorComponents[0], green: colorComponents[1], blue: colorComponents[2], alpha: 1)
  }
}

extension CIContext {
  func renderedImage(from image: CIImage, extent: CGRect) -> [UInt8]? {
    var bitmap = [UInt8](repeating: 0, count: 4)
    render(image, toBitmap: &bitmap, rowBytes: 4, bounds: extent, format: .RGBA8, colorSpace: CGColorSpaceCreateDeviceRGB())
    return bitmap
  }
}

#Preview {
    BookGrandImageView(bookImage: .init(
      imageURL: "https://storage.googleapis.com/books_media/covers/liubits-noch-prava-patsukou.jpg",
      authorName: "Іван Свістулькін",
      title: "Доўгая дарога дадому",
      gradientColors: [.pink, .purple]))
}

