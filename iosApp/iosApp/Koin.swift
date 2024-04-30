
import Shared

final class Koin {
  private var core: Koin_coreKoin?

  static let instance = Koin()

  static func start() {
    if instance.core == nil {
      instance.core = KoinCommonKt.doInitKoin()
    }
    if instance.core == nil {
      fatalError("Can't initialize Koin.")
    }
  }

  private init() {
  }

  func get<T: AnyObject>() -> T {
    guard let core else {
      fatalError("You should call `start()` before using \(#function)")
    }

    guard let result = core.get(objCClass: T.self) as? T else {
      fatalError("Koin can't provide an instance of type: \(T.self)")
    }

    return result
  }
}

