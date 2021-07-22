package com.akhttp.chordiagram.util

//class ChordView(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs) {
//
//    private var _chord: Chord? = null
//    private lateinit var binding: ChordListItemBinding
//
//    init {
//        val inflater = LayoutInflater.from(this.context)
//        binding = ChordListItemBinding.inflate(inflater, this, true)
//    }
//
//    fun setChord(chord: Chord) {
//        _chord = chord
//        this.invalidate()
//    }
//
//    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
//
//        _chord?.let {
//            if (it.startingPosition == 0) {
//                binding.startingPosition.isVisible = true
//                binding.startingPosition.text = it.startingPosition.toString()
//            }
//            binding.chordName.text = it.chordName
//
//            it.notes.forEach {
//            }
//
//        }
//
//
//
//    }
//
//}